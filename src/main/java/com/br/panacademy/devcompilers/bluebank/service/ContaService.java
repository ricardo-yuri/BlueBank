package com.br.panacademy.devcompilers.bluebank.service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import com.br.panacademy.devcompilers.bluebank.entity.Cliente;
import com.br.panacademy.devcompilers.bluebank.repository.ClienteRepository;
import com.br.panacademy.devcompilers.bluebank.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.panacademy.devcompilers.bluebank.dto.ContaDTO;
import com.br.panacademy.devcompilers.bluebank.entity.Conta;
import com.br.panacademy.devcompilers.bluebank.repository.ContaRepository;
import com.br.panacademy.devcompilers.bluebank.utils.Mapper;

@Service
public class ContaService {
	
	@Autowired
	private ContaRepository contaRespository;
	@Autowired
	private ClienteRepository clienteRespository;
	@Autowired
	private DateUtil dateUtil;
	@Autowired
	private HistoricoService historicoService;

	public ContaDTO createConta(ContaDTO contaDTO) {
		Conta conta = new Conta();

		Optional<Cliente> cliente = clienteRespository.findByCpf(contaDTO.getCpfUsuario());

		if(cliente.isPresent()) {
			conta = Mapper.contaToEntity(contaDTO);
			conta.setCliente(cliente.get());
		} else {
			throw new NoSuchElementException("Cliente não encontrado");
		}

		Conta contaToSave = contaRespository.save(conta);
		return Mapper.contaToDTO(contaToSave);
	}
	
	public ContaDTO findByIdConta(Long id) {
		Conta conta = verifyIfExists(id);

		return Mapper.contaToDTO(conta);
	}
	
	public ContaDTO updateConta(ContaDTO contaDTO) {
		Conta conta = verifyIfExists(contaDTO.getId());
		Conta contaSaved = contaRespository.save(conta);

		return Mapper.contaToDTO(contaSaved);
	}
	
	public String deleteConta(Long id) {
		Conta contaToDelete = verifyIfExists(id);

		contaRespository.delete(contaToDelete);

		return String.format("Conta con Id: %d deletada!", id);
	}
	
	//Método sacar
	public String sacarConta(Long idConta, double valor) {
		//Conta existe?
		Conta conta = verifyIfExists(idConta);

		double saldoAtual = conta.getSaldo();
		
		if(isSaldoSuficiente(saldoAtual, valor)) {
			saldoAtual -= valor;
			conta.setSaldo(saldoAtual);
			contaRespository.save(conta);

			String logToSave = dateUtil.dateFormatted(LocalDateTime.now()).concat(
					String.format(" Saque -> conta %s, valor: %f", conta.getNumeroConta(), valor));
			historicoService.adicionaLog(logToSave, idConta, "saque");

			return "Saque realizado!";
		}else {
			return "Saldo insuficiente!";
		}
	}
	
	//Método depositar
	public String depositaConta(Long idConta, double valor) {
		//Conta existe?
		if(valor < 0.01) return "Valor deposito inválido!"; 
				
		Conta conta = verifyIfExists(idConta);
		double saldoAtual = conta.getSaldo();
		
		conta.setSaldo(saldoAtual + valor);
		contaRespository.save(conta);

		String logToSave = dateUtil.dateFormatted(LocalDateTime.now()).concat(
				String.format(" Deposito -> conta %s, valor: %f", conta.getNumeroConta(), valor));
		historicoService.adicionaLog(logToSave, idConta, "deposito");

		return "deposito realizado!";
	}
	
	//Método transferir
	//TODO Revisar a anotação @Transactional
	@Transactional
	public String transfereConta(Long idContaOrigem, Long idContaDestino, double valor) {
		//ContaOrigem e ContaDestino existem?
		Conta contaOrigem = verifyIfExists(idContaOrigem);
		Conta contaDestino = verifyIfExists(idContaDestino);
		
		double saldoAtualContaOrigem = contaOrigem.getSaldo();
		
		if(isSaldoSuficiente(saldoAtualContaOrigem, valor)) {
			contaOrigem.setSaldo(saldoAtualContaOrigem - valor);
			contaRespository.save(contaOrigem);
			
			double saldoAtualContaDestino = contaDestino.getSaldo();
			contaDestino.setSaldo(saldoAtualContaDestino + valor);
			contaRespository.save(contaDestino);

			String logToSave = dateUtil.dateFormatted(LocalDateTime.now()).concat(
					String.format(" Transferência: -> conta %s para conta %s, valor: %f",
							contaOrigem.getNumeroConta(),
							contaDestino.getNumeroConta(),
							valor)
			);
			historicoService.adicionaLog(logToSave, idContaOrigem, "transferencia");

			return "Transferência realizada com sucesso!";
		}else {
			return "Saldo insuficiente para a transferência!";
		}
		
	}
	
	private Conta verifyIfExists(Long id) {
		return contaRespository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Conta não encontrada."));
	}
	
	private boolean isSaldoSuficiente(double saldo, double valor) {
		if((saldo - valor) > 0) 
			return true;
		
		return false;
	}
}
