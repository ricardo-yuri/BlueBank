package com.br.panacademy.devcompilers.bluebank.utils;

import org.springframework.http.ResponseEntity;

import com.br.panacademy.devcompilers.bluebank.dto.ClienteDTO;
import com.br.panacademy.devcompilers.bluebank.dto.ContaDTO;
import com.br.panacademy.devcompilers.bluebank.dto.EnderecoDTO;
import com.br.panacademy.devcompilers.bluebank.entity.Cliente;
import com.br.panacademy.devcompilers.bluebank.entity.Conta;
import com.br.panacademy.devcompilers.bluebank.entity.Endereco;

public class Mapper {

	public static Cliente toCliente(ClienteDTO clienteDTO) {
		
		Cliente cliente = new Cliente();
		
		cliente.setId(clienteDTO.getId());
		cliente.setNome(clienteDTO.getNome());
		cliente.setCpf(clienteDTO.getCpf());
		cliente.setRg(clienteDTO.getRg());
		cliente.setEndereco(enderecoToEntity(clienteDTO.getEndereco()));
		cliente.setTipoCliente(clienteDTO.getTipoCliente());
		cliente.setCreateAt(clienteDTO.getCreateAt());
		
		return cliente;
	}
	
	public static ClienteDTO toDTO(Cliente cliente) {
		
		ClienteDTO clienteDTO = new ClienteDTO();
		
		clienteDTO.setId(cliente.getId());
		clienteDTO.setNome(cliente.getNome());
		clienteDTO.setCpf(cliente.getCpf());
		clienteDTO.setRg(cliente.getRg());
		clienteDTO.setEndereco(enderecoToDTO(cliente.getEndereco()));
		clienteDTO.setTipoCliente(cliente.getTipoCliente());
		clienteDTO.setCreateAt(cliente.getCreateAt());
		
		return clienteDTO;
	}
	
	public static EnderecoDTO enderecoToDTO(Endereco endereco) {
		EnderecoDTO enderecoDTO = new EnderecoDTO();
		
		enderecoDTO.setId(endereco.getId());
		enderecoDTO.setCep(endereco.getCep());
		enderecoDTO.setLogradouro(endereco.getLogradouro());
		enderecoDTO.setComplemento(endereco.getComplemento());
		enderecoDTO.setLocalidade(endereco.getLocalidade());
		enderecoDTO.setUf(endereco.getUf());
		enderecoDTO.setBairro(endereco.getBairro());
		
		return enderecoDTO;
	}
	
	public static Endereco enderecoToEntity(EnderecoDTO enderecoDTO) {
		Endereco enderecoEntity = new Endereco();
		
		enderecoEntity.setId(enderecoDTO.getId());
		enderecoEntity.setCep(enderecoDTO.getCep());
		enderecoEntity.setLogradouro(enderecoDTO.getLogradouro());
		enderecoEntity.setComplemento(enderecoDTO.getComplemento());
		enderecoEntity.setLocalidade(enderecoDTO.getLocalidade());
		enderecoEntity.setUf(enderecoDTO.getUf());
		enderecoEntity.setBairro(enderecoDTO.getBairro());
		
		return enderecoEntity;
	}
	
	public static Endereco responseEntityEnderecoToEntity(ResponseEntity<EnderecoDTO> enderecoDTO) {
		Endereco enderecoEntity = new Endereco();
		
		enderecoEntity.setId(enderecoDTO.getBody().getId());
		enderecoEntity.setCep(enderecoDTO.getBody().getCep());
		enderecoEntity.setLogradouro(enderecoDTO.getBody().getLogradouro());
		enderecoEntity.setComplemento(enderecoDTO.getBody().getComplemento());
		enderecoEntity.setLocalidade(enderecoDTO.getBody().getLocalidade());
		enderecoEntity.setUf(enderecoDTO.getBody().getUf());
		enderecoEntity.setBairro(enderecoDTO.getBody().getBairro());
		
		return enderecoEntity;
	}
	
	public static EnderecoDTO responseEntityEnderecoToDTO(ResponseEntity<EnderecoDTO> endereco) {
		EnderecoDTO enderecoDTO = new EnderecoDTO();
		
		enderecoDTO.setId(endereco.getBody().getId());
		enderecoDTO.setCep(endereco.getBody().getCep());
		enderecoDTO.setLogradouro(endereco.getBody().getLogradouro());
		enderecoDTO.setComplemento(endereco.getBody().getComplemento());
		enderecoDTO.setLocalidade(endereco.getBody().getLocalidade());
		enderecoDTO.setUf(endereco.getBody().getUf());
		enderecoDTO.setBairro(endereco.getBody().getBairro());
		
		return enderecoDTO;
	}
	
	public static Conta contaToEntity(ContaDTO contaDTO) {
		Conta contaEntity = new Conta();
		
		contaEntity.setId(contaDTO.getId());
		contaEntity.setAgencia(contaDTO.getAgencia());
		contaEntity.setNumero(contaDTO.getNumero());
		contaEntity.setTipoConta(contaDTO.getTipoConta());
		contaEntity.setSenha(contaDTO.getSenha());
		contaEntity.setCreateAt(contaDTO.getCreateAt());
		
		return contaEntity;
	}
	
	public static ContaDTO contaToDTO(Conta contaEntity) {
		ContaDTO contaDTO = new ContaDTO();
		
		contaDTO.setId(contaEntity.getId());
		contaDTO.setAgencia(contaEntity.getAgencia());
		contaDTO.setNumero(contaEntity.getNumero());
		contaDTO.setTipoConta(contaEntity.getTipoConta());
		contaDTO.setSenha(contaEntity.getSenha());
		contaDTO.setCreateAt(contaEntity.getCreateAt());
		
		return contaDTO;
	}
}
