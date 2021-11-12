package com.br.panacademy.devcompilers.bluebank.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.br.panacademy.devcompilers.bluebank.dto.ClienteDTO;
import com.br.panacademy.devcompilers.bluebank.dto.EnderecoDTO;
import com.br.panacademy.devcompilers.bluebank.entity.Cliente;
import com.br.panacademy.devcompilers.bluebank.repository.ClienteRepository;
import com.br.panacademy.devcompilers.bluebank.repository.EnderecoRepository;
import com.br.panacademy.devcompilers.bluebank.utils.Mapper;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	EnderecoRepository enderecoRepository; // TODO VERIFICAR A NECESSIDADE

	// TODO ADICIONAR EXCEPTION CASO NÃO EXISTA CLIENTES
	public List<ClienteDTO> findAll() {
		return clienteRepository.findAll().stream().map(Mapper::toDTO).collect(Collectors.toList());
	}

	public ClienteDTO findById(Long id) {
		Cliente cliente = verifyIfExists(id);

		return Mapper.toDTO(cliente);
	}

	@Transactional
	public ClienteDTO create(ClienteDTO clienteDTO) {
		Cliente cliente = clienteRepository.save(Mapper.toCliente(clienteDTO));
		return Mapper.toDTO(cliente);
	}

	public ClienteDTO updateCliente(ClienteDTO clienteDTO) {
		Cliente cliente = verifyIfExists(clienteDTO.getId());

		enderecoRepository.save(cliente.getEndereco());

		Cliente clienteSaved = clienteRepository.save(cliente);

		return Mapper.toDTO(clienteSaved);
	}

	public String deleteCliente(Long id) {
		verifyIfExists(id);

		clienteRepository.deleteById(id);

		return String.format("Cliente com ID: %d deletado!", id);
	}

	public EnderecoDTO findEnderecoByCep(Integer cep) {
		RestTemplate restTemplate = new RestTemplate();
		String viaCep = "https://viacep.com.br/ws/";
		ResponseEntity<String> response = restTemplate.getForEntity(viaCep + cep + "/json/", String.class);
		EnderecoDTO endereco = new EnderecoDTO();
		System.out.println(response.getBody());
		return endereco;
		
	}

	private Cliente verifyIfExists(Long id) {
		return clienteRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
	}

}
