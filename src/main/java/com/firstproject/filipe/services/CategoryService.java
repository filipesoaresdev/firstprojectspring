package com.firstproject.filipe.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.firstproject.filipe.domain.Category;
import com.firstproject.filipe.repositories.CategoryRepository;
import com.firstproject.filipe.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	
	public Category buscar(Integer id) {
		
		Optional<Category> object = categoryRepository.findById(id);
		
		return object.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id: "+ id + "Tipo: "+ Category.class.getName()));
		
	}
	
	
}
