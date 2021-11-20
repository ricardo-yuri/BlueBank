package com.br.panacademy.devcompilers.bluebank.service;

import java.util.NoSuchElementException;

import javax.transaction.Transactional;

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
	
	public ContaDTO createConta(ContaDTO contaDTO) {
		Conta conta = contaRespository.save(Mapper.contaToEntity(contaDTO));
		return Mapper.contaToDTO(conta);
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
