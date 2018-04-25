package com.firstproject.filipe.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.firstproject.filipe.domain.Client;
import com.firstproject.filipe.repositories.ClientRepository;
import com.firstproject.filipe.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	public Client find(Integer id) {
		Optional<Client> client = clientRepository.findById(id);
		return client.orElseThrow(() ->new ObjectNotFoundException(
				"Objeto não encontrado! id: "+ id + "Tipo: "+ Client.class.getName()) );
	}
	
}
