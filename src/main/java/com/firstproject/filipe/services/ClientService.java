package com.firstproject.filipe.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.firstproject.filipe.domain.Client;
import com.firstproject.filipe.dto.ClientDTO;
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
	
	//Get Client from databasbe by id
	public Client find(Integer id) {
		Optional<Client> client = clientRepository.findById(id);
		return client.orElseThrow(() ->new ObjectNotFoundException(
				"Objeto não encontrado! id: "+ id + "Tipo: "+ Client.class.getName()) );
	}
	
	public Client fromDTO(ClientDTO clientDTO) {
		return new Client(clientDTO.getId(), clientDTO.getName(), clientDTO.getEmail()
				, null, null);
	}
	
	public Client update(Client client) {
		//Get the client from database
		Client clientAux = find(client.getId());
		//update the data passed in the PUT method on the recovered clientAux from the database
		updateData(clientAux, client);
		return clientRepository.save(clientAux);
	}
	
	private void updateData(Client clientAux, Client client) {
		clientAux.setName(client.getName());
		clientAux.setEmail(client.getEmail());
		
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
	
	
}
