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

import com.firstproject.filipe.domain.Category;
import com.firstproject.filipe.dto.CategoryDTO;
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
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoryDTO categoryDTO){ //The @RequestBody annotation 
																		//is declared to convert json on object
		Category category = service.fromDTO(categoryDTO);
		category = service.insert(category);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(category.getId())
				.toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody CategoryDTO categoryDTO, 
										@PathVariable Integer id){
	    Category category = service.fromDTO(categoryDTO);
		category.setId(id);
		category = service.update(category);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CategoryDTO>> findAll(){ 
		
		List<Category> listaCategorias = service.findAll();
		List<CategoryDTO> listCategoryDTOs = listaCategorias.stream()
				.map(category -> new CategoryDTO(category)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listCategoryDTOs);
		
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/page")
	public ResponseEntity<Page<CategoryDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage",defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy",defaultValue="nome") String orderBy,
			@RequestParam(value="direction",defaultValue="ASC") String direction){
		Page<Category> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<CategoryDTO> listCategoryDTO = list
				.map(category -> new CategoryDTO(category));
	
		return ResponseEntity.ok().body(listCategoryDTO);
	
	}

}
