package com.br.panacademy.devcompilers.bluebank.service;

import java.util.NoSuchElementException;

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
		verifyIfExists(id);

		contaRespository.deleteById(id);

		return String.format("Conta com ID: %d deletado!", id);
	}
	
	private Conta verifyIfExists(Long id) {
		return contaRespository.findById(id).orElseThrow(() -> new NoSuchElementException());
	}
}
