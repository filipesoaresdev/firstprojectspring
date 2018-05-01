package com.firstproject.filipe.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.firstproject.filipe.domain.Client;
import com.firstproject.filipe.repositories.ClientRepository;
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
	
}
