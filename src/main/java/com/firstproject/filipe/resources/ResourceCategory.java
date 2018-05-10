package com.firstproject.filipe.resources;


import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	public ResponseEntity<Category> find(@PathVariable Integer id) {
		
		Category category = service.find(id);
	
		return ResponseEntity.ok().body(category);

		
	}
	//This annotation says that here is a Post Request.
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Category category){ //The @RequestBody annotation 
																		//is declared to convert json on object
		
		category = service.insert(category);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(category.getId())
				.toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Category category, @PathVariable Integer id){
	    category.setId(id);
		category = service.update(category);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	

}
