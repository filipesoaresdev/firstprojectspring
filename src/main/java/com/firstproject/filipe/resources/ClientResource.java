package com.firstproject.filipe.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.firstproject.filipe.domain.Client;
import com.firstproject.filipe.dto.ClientDTO;
import com.firstproject.filipe.dto.ClientNewDTO;
import com.firstproject.filipe.services.ClientService;

@RestController
@RequestMapping(value="/client")
public class ClientResource {

	@Autowired
	private ClientService clientService;
	
	
	//Find Client by id
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Client> find(@PathVariable Integer id){
		
		Client client = clientService.find(id);
		
		return ResponseEntity.ok().body(client);
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClientNewDTO clientNewDTO){
		Client client = clientService.fromClientNewDTO(clientNewDTO);
		client = clientService.insert(client);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(client.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		clientService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClientDTO clientDTO, @PathVariable Integer id){
		Client client = clientService.fromDTO(clientDTO);
		client.setId(id);
		client  = clientService.update(client);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ClientDTO>> findAll(){
		
		List<Client> listClient = clientService.findAll();
		List<ClientDTO> listClientDTO = listClient.stream()
				.map(client -> new ClientDTO(client)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listClientDTO);
		
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<ClientDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy",defaultValue="name") String orderBy,
			@RequestParam(value="direction",defaultValue="ASC") String direction
			){
		Page<Client> list = clientService.findPage(page,linesPerPage, orderBy,direction);
		Page<ClientDTO> listClientDTO = list.map(client -> new ClientDTO(client));
		
		return ResponseEntity.ok().body(listClientDTO);
	}
	
	
	
	
	
}
