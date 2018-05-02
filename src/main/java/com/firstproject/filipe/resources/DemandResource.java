package com.firstproject.filipe.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.firstproject.filipe.domain.Demand;
import com.firstproject.filipe.services.DemandService;

@RestController
@RequestMapping(value="/demands")
public class DemandResource {

	@Autowired
	private DemandService demandService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id){
		Demand demand = demandService.find(id);
		return ResponseEntity.ok().body(demand);
	}
}
