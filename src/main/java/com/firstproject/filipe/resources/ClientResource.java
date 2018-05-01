package com.firstproject.filipe.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.firstproject.filipe.domain.Client;
import com.firstproject.filipe.services.ClientService;

@RestController
@RequestMapping(value="/client")
public class ClientResource {

	@Autowired
	private ClientService clientService;
	
	
	//Find Client by id
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id){
		
		Client client = clientService.find(id);
		
		return ResponseEntity.ok().body(client);
		
	}
	
	
}
