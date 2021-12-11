package com.br.panacademy.devcompilers.bluebank.service;

import com.br.panacademy.devcompilers.bluebank.dto.AddressDTO;
import com.br.panacademy.devcompilers.bluebank.dto.ClientDTO;
import com.br.panacademy.devcompilers.bluebank.entity.Account;
import com.br.panacademy.devcompilers.bluebank.entity.Client;
import com.br.panacademy.devcompilers.bluebank.exceptions.ClientNotFoundException;
import com.br.panacademy.devcompilers.bluebank.exceptions.OperationIllegalException;
import com.br.panacademy.devcompilers.bluebank.repository.AccountRepository;
import com.br.panacademy.devcompilers.bluebank.repository.AddressRepository;
import com.br.panacademy.devcompilers.bluebank.repository.ClientRepository;
import com.br.panacademy.devcompilers.bluebank.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private AddressRepository addressRepository;

	public List<ClientDTO> findAllClient() {
		return clientRepository.findAll().stream().map(Mapper::toClientDTO).collect(Collectors.toList());
	}

	public ClientDTO findByIdClient(Long id) {
		Client client = verifyIfExists(id);
		return Mapper.toClientDTO(client);
	}

	public Optional<Client> findCPFClient(String cpf) {
		return clientRepository.findByCpf(cpf);
	}

	@Transactional
	public ClientDTO createClient(ClientDTO clientDTO) {
		if(clientRepository.findByCpf(clientDTO.getCpfUser()).isPresent())
			throw new OperationIllegalException("Cliente já cadastrado!");

		Client client = clientRepository.save(Mapper.toClient(clientDTO));

		return Mapper.toClientDTO(client);
	}

	public ClientDTO updateClient(ClientDTO clientDTO) {
		verifyIfExists(clientDTO.getId());
		Client client = Mapper.toClient(clientDTO);
		System.out.println(client.getContato().getCell());
		addressRepository.save(client.getEndereco());
		Client clientSaved = clientRepository.save(client);
		return Mapper.toClientDTO(clientSaved);
	}

	public String deleteClient(Long id) {
		Client client = verifyIfExists(id);

		List<Account> accounts = accountRepository.findByClientIdAndDeletedIsFalse(id);

		if(!accounts.isEmpty()) {
			for(Account account : accounts) {
				if(!account.isDeleted()) {
					throw new NoSuchElementException("Não é possivel deletar um cliente com a conta ativa!");
				}
			}
		}

		client.setDeleted(true);
		clientRepository.save(client);
		return String.format("Cliente com ID: %d deletado!", id);
	}

	public AddressDTO findAddressByCep(Integer cep) {
		RestTemplate restTemplate = new RestTemplate();
		String viaCep = "https://viacep.com.br/ws/";
		ResponseEntity<AddressDTO> response = restTemplate.getForEntity(viaCep + cep + "/json/", AddressDTO.class);
		return Mapper.responseEntityAddressToDTO(response);
		
	}

	private Client verifyIfExists(Long id) {
		return clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException(id));
	}

}