package com.br.panacademy.devcompilers.bluebank.utils;

import com.br.panacademy.devcompilers.bluebank.dto.*;
import com.br.panacademy.devcompilers.bluebank.entity.*;
import org.springframework.http.ResponseEntity;

public class Mapper {

	public static Cliente toCliente(ClienteDTO clienteDTO) {
		
		Cliente cliente = new Cliente();

		cliente.setId(clienteDTO.getId());
		cliente.setNome(clienteDTO.getNome());
		cliente.setCpf(clienteDTO.getCpf());
		cliente.setRg(clienteDTO.getRg());
		cliente.setContato(contatoToEntity(clienteDTO.getContato()));
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
		clienteDTO.setContato(contatoToDTO(cliente.getContato()));
		clienteDTO.setEndereco(enderecoToDTO(cliente.getEndereco()));
		clienteDTO.setTipoCliente(cliente.getTipoCliente());
		clienteDTO.setCreateAt(cliente.getCreateAt());
		
		return clienteDTO;
	}

	public static ContatoDTO contatoToDTO(Contato contatoEntity) {
		ContatoDTO contatoDTO = new ContatoDTO();

		contatoDTO.setId(contatoEntity.getId());
		contatoDTO.setEmail(contatoEntity.getEmail());
		contatoDTO.setTelefone(contatoEntity.getTelefone());
		contatoDTO.setCelular(contatoEntity.getCelular());
		contatoDTO.setCreateAt(contatoEntity.getCreateAt());

		return contatoDTO;
	}

	public static Contato contatoToEntity(ContatoDTO contatoDTO) {
		Contato contato = new Contato();

		contato.setId(contatoDTO.getId());
		contato.setEmail(contatoDTO.getEmail());
		contato.setTelefone(contatoDTO.getTelefone());
		contato.setCelular(contatoDTO.getCelular());
		contato.setCreateAt(contatoDTO.getCreateAt());

		return contato;
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
		contaEntity.setNumeroConta(contaDTO.getNumeroConta());
		contaEntity.setTipoConta(contaDTO.getTipoConta());
		contaEntity.setSaldo(contaDTO.getSaldo());
		contaEntity.setSenha(contaDTO.getSenha());
		contaEntity.setCreateAt(contaDTO.getCreateAt());
		
		return contaEntity;
	}
	
	public static ContaDTO contaToDTO(Conta contaEntity) {
		ContaDTO contaDTO = new ContaDTO();

		contaDTO.setId(contaEntity.getId());
		contaDTO.setCpfUsuario(contaEntity.getCliente().getCpf());
		contaDTO.setAgencia(contaEntity.getAgencia());
		contaDTO.setNumeroConta(contaEntity.getNumeroConta());
		contaDTO.setTipoConta(contaEntity.getTipoConta());
		contaDTO.setSaldo(contaEntity.getSaldo());
		contaDTO.setSenha(contaEntity.getSenha());
		contaDTO.setCreateAt(contaEntity.getCreateAt());
		
		return contaDTO;
	}

	public static HistoricoDTO historicoToDTO(Historico historicoEntity) {
		HistoricoDTO historicoDTO = new HistoricoDTO();

		historicoDTO.setId(historicoEntity.getId());
		historicoDTO.setLog(historicoEntity.getLog());
		historicoDTO.setIdConta(historicoEntity.getIdConta());
		historicoDTO.setTipo(historicoEntity.getTipo());
		historicoDTO.setCreateAt(historicoEntity.getCreateAt());

		return historicoDTO;
	}

	public static Historico historicoToEntity(HistoricoDTO historicoDTO) {
		Historico historicoEntity = new Historico();

		historicoEntity.setId(historicoDTO.getId());
		historicoEntity.setLog(historicoDTO.getLog());
		historicoEntity.setIdConta(historicoDTO.getIdConta());
		historicoEntity.setTipo(historicoDTO.getTipo());
		historicoEntity.setCreateAt(historicoDTO.getCreateAt());

		return historicoEntity;
	}
}
