package com.firstproject.filipe.resources;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.firstproject.filipe.domain.Category;
import com.firstproject.filipe.services.CategoryService;


//The annotation declare that this is a Rest Controller
@RestController
@RequestMapping(value="/categories")
public class ResourceCategory {
	
	@Autowired
	private CategoryService service;
	
	//Find Category by id
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		Category category = service.buscar(id);
	
		return ResponseEntity.ok().body(category);

		
	}

}
