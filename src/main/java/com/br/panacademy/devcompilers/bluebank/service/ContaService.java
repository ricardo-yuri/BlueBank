package com.br.panacademy.devcompilers.bluebank.service;

import com.br.panacademy.devcompilers.bluebank.dto.ContaDTO;
import com.br.panacademy.devcompilers.bluebank.entity.Cliente;
import com.br.panacademy.devcompilers.bluebank.entity.Conta;
import com.br.panacademy.devcompilers.bluebank.exceptions.OperacaoIlegalException;
import com.br.panacademy.devcompilers.bluebank.repository.ClienteRepository;
import com.br.panacademy.devcompilers.bluebank.repository.ContaRepository;
import com.br.panacademy.devcompilers.bluebank.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ContaService {
	
	@Autowired
	private ContaRepository contaRespository;
	@Autowired
	private ClienteRepository clienteRespository;

	@Autowired
	private HistoricoService historicoService;

	public ContaDTO createConta(ContaDTO contaDTO) {
		Conta conta;

		Optional<Cliente> cliente = clienteRespository.findByCpf(contaDTO.getCpfUsuario());

		if(cliente.isPresent()) {
			conta = Mapper.contaToEntity(contaDTO);
			conta.setCliente(cliente.get());
			conta.setNumeroConta(geraNumeroConta());
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
		Conta contaUpdate = verifyIfExists(contaDTO.getId());

		//Não deve permitir alterar o titular da conta
		if(!contaUpdate.getCliente().getCpf().equals(contaDTO.getCpfUsuario())) {
			throw new OperacaoIlegalException("Não é permitido mudar o titular da conta!");
		}

		//Não deve permitir alterar o número da conta
		if(!contaUpdate.getNumeroConta().equals(contaDTO.getNumeroConta())) {
			throw new OperacaoIlegalException("Não é permitido alterar o número da conta!");
		}

		Conta contaSaved = contaRespository.save(contaUpdate);

		return Mapper.contaToDTO(contaSaved);
	}
	
	public String deleteConta(Long id) {
		Conta contaToDelete = verifyIfExists(id);

		if(contaToDelete.getSaldo() > 0) {
			throw new OperacaoIlegalException("Não é permitido deletar uma conta com saldo!");
		}

		contaToDelete.setDeletada(true);
		contaRespository.save(contaToDelete);

		return String.format("Conta com Id: %d deletada!", id);
	}

	//Método sacar
	public String sacarConta(Long idConta, double valor) {

		if(isValorInvalido(valor)) return "Valor para saque inválido!";

		Conta conta = verifyIfExists(idConta);

		double saldoAtual = conta.getSaldo();
		
		if(isSaldoSuficiente(saldoAtual, valor)) {
			saldoAtual -= valor;
			conta.setSaldo(saldoAtual);

			contaRespository.save(conta);

			String logToSave =
					String.format(MessageFormat.format("{0} Saque -> conta %s, valor: %f",
							LocalDateTime.now()), conta.getNumeroConta(), valor);
			historicoService.adicionaLog(logToSave, idConta, "saque");

			return "Saque realizado!";
		}else {
			return "Saldo insuficiente!";
		}
	}
	
	//Método depositar
	public String depositaConta(Long idConta, double valor) {

		if(isValorInvalido(valor)) return "Valor para deposito inválido!";
				
		Conta conta = verifyIfExists(idConta);
		double saldoAtual = conta.getSaldo();
		
		conta.setSaldo(saldoAtual + valor);
		contaRespository.save(conta);

		String logToSave =
				String.format(MessageFormat.format("{0} Deposito -> conta %s, valor: %f",
						LocalDateTime.now()), conta.getNumeroConta(), valor);
		historicoService.adicionaLog(logToSave, idConta, "deposito");

		return "deposito realizado!";
	}
	
	//Método transferir
	//TODO Revisar a anotação @Transactional
	@Transactional
	public String transfereConta(Long idContaOrigem, Long idContaDestino, double valor) {

		if(isValorInvalido(valor)) return "Valor para transferência inválido!";

		Conta contaOrigem = verifyIfExists(idContaOrigem);
		Conta contaDestino = verifyIfExists(idContaDestino);
		
		double saldoAtualContaOrigem = contaOrigem.getSaldo();
		
		if(isSaldoSuficiente(saldoAtualContaOrigem, valor)) {
			contaOrigem.setSaldo(saldoAtualContaOrigem - valor);
			contaRespository.save(contaOrigem);
			
			double saldoAtualContaDestino = contaDestino.getSaldo();
			contaDestino.setSaldo(saldoAtualContaDestino + valor);
			contaRespository.save(contaDestino);

			String logToSave =
					String.format(MessageFormat.format("{0} Transferência: -> conta %s para conta %s, valor: %f",
							LocalDateTime.now()),
							contaOrigem.getNumeroConta(),
							contaDestino.getNumeroConta(),
							valor);

			historicoService.adicionaLog(logToSave, idContaOrigem, "transferencia");

			return "Transferência realizada com sucesso!";
		}else {
			return "Saldo insuficiente para a transferência!";
		}
		
	}
	
	private Conta verifyIfExists(Long id) {
		return contaRespository.findByIdAndDeletadaIsFalse(id)
				.orElseThrow(() -> new NoSuchElementException(String.format("Conta com ID: %d não encontrada!", id)));
	}
	
	private boolean isSaldoSuficiente(double saldo, double valor) {
		if((saldo - valor) >= 0) return true;

		return false;
	}

	private boolean isValorInvalido(double valor) {
		if(valor < 0.01) return true;

		return false;
	}

	private String geraNumeroConta() {
		return new Timestamp(new Date().getTime()).toString();
	}
}
