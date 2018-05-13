package com.firstproject.filipe.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.firstproject.filipe.domain.Address;
import com.firstproject.filipe.domain.City;
import com.firstproject.filipe.domain.Client;
import com.firstproject.filipe.domain.enums.ClientType;
import com.firstproject.filipe.dto.ClientDTO;
import com.firstproject.filipe.dto.ClientNewDTO;
import com.firstproject.filipe.repositories.AddressRepository;
import com.firstproject.filipe.repositories.ClientRepository;
import com.firstproject.filipe.services.exceptions.DataIntegrityException;
import com.firstproject.filipe.services.exceptions.ObjectNotFoundException;
/**
 * 
 * @author Filipe
 *
 */
@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private AddressRepository addressRepository;
	
	//Get Client from databasbe by id
	public Client find(Integer id) {
		Optional<Client> client = clientRepository.findById(id);
		return client.orElseThrow(() ->new ObjectNotFoundException(
				"Objeto não encontrado! id: "+ id + "Tipo: "+ Client.class.getName()) );
	}

	@Transactional
	public Client insert(Client client) {
		client.setId(null);
		client =  clientRepository.save(client);
		addressRepository.saveAll(client.getAddresses());
		return client;
	}
	
	public Client update(Client client) {
		//Get the client from database
		Client clientAux = find(client.getId());
		//update the data passed in the PUT method on the recovered clientAux from the database
		updateData(clientAux, client);
		return clientRepository.save(clientAux);
	}

	public List<Client> findAll(){
		return clientRepository.findAll();
	}
	
	public void delete(Integer id) {
		find(id);
		try {
		clientRepository.deleteById(id);
		}catch (DataIntegrityException e) {
			//Temporary Exception
			throw new DataIntegrityException("Can not delete a client with dependent items.");
		}
	}
	
	public Page<Client> findPage(Integer page, Integer linesPerPage,String orderBy, String direction){
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage,Direction.valueOf(direction), orderBy);
		return clientRepository.findAll(pageRequest);
	}
	
	private void updateData(Client clientAux, Client client) {
		clientAux.setName(client.getName());
		clientAux.setEmail(client.getEmail());
		
	}
	
	public Client fromDTO(ClientDTO clientDTO) {
		return new Client(clientDTO.getId(), clientDTO.getName(), clientDTO.getEmail()
				, null, null);
	}

	public Client fromClientNewDTO(@Valid ClientNewDTO objDTO) {
		Client client = new Client(null, objDTO.getName(), 
				objDTO.getEmail(), objDTO.getCpfOrCnpj(),
				ClientType.toEnum(objDTO.getClientType()));
		City city = new City(objDTO.getCidadeId(), null, null);
		Address address = new Address(null, objDTO.getStreet(),
				objDTO.getNumber(),
				objDTO.getComplement(),
				objDTO.getNeighborhood(),
				objDTO.getZipCode(),
				client,
				city);
		client.getAddresses().add(address);
		
		client.getPhones().add(objDTO.getTelefone1());
		if(objDTO.getTelefone2()!=null) {
			client.getPhones().add(objDTO.getTelefone2());
		}
		if(objDTO.getTelefone3()!=null) {
			client.getPhones().add(objDTO.getTelefone3());
		}
		
		return client;
	}
	
	
}
