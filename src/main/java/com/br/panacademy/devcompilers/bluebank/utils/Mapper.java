package com.br.panacademy.devcompilers.bluebank.utils;

import com.br.panacademy.devcompilers.bluebank.dto.ClienteDTO;
import com.br.panacademy.devcompilers.bluebank.dto.EnderecoDTO;
import com.br.panacademy.devcompilers.bluebank.entity.Cliente;
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
	
	private static EnderecoDTO enderecoToDTO(Endereco endereco) {
		EnderecoDTO enderecoDTO = new EnderecoDTO();
		
		enderecoDTO.setId(endereco.getId());
		enderecoDTO.setCep(endereco.getCep());
		enderecoDTO.setLogradouro(endereco.getLogradouro());
		enderecoDTO.setComplemento(endereco.getComplemento());
		enderecoDTO.setLocalidade(endereco.getLocalidade());
		
		return enderecoDTO;
	}
	
	private static Endereco enderecoToEntity(EnderecoDTO enderecoDTO) {
		Endereco enderecoEntity = new Endereco();
		
		enderecoEntity.setId(enderecoDTO.getId());
		enderecoEntity.setCep(enderecoDTO.getCep());
		enderecoEntity.setLogradouro(enderecoDTO.getLogradouro());
		enderecoEntity.setComplemento(enderecoDTO.getComplemento());
		enderecoEntity.setLocalidade(enderecoDTO.getLocalidade());
		
		return enderecoEntity;
	}
	
}
