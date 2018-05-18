package com.firstproject.filipe.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.firstproject.filipe.domain.Demand;
import com.firstproject.filipe.services.DemandService;

@RestController
@RequestMapping(value="/demands")
public class DemandResource {

	@Autowired
	private DemandService demandService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Demand> find(@PathVariable Integer id){
		Demand demand = demandService.find(id);
		return ResponseEntity.ok().body(demand);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Demand demand){
		
		demand = demandService.insert(demand);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(demand.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
}
