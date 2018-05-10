package com.firstproject.filipe.services;
/**
 * @author Filipe
 * 
 */
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.firstproject.filipe.domain.Category;
import com.firstproject.filipe.repositories.CategoryRepository;
import com.firstproject.filipe.services.exceptions.DataIntegrityException;
import com.firstproject.filipe.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	//Get the category from the database by id
	public Category find(Integer id) {
		
		Optional<Category> object = categoryRepository.findById(id);
		
		return object.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id: "+ id + "Tipo: "+ Category.class.getName()));
		
	}

	public Category insert(Category category) {
		category.setId(null);
		return categoryRepository.save(category);
	}

	public Category update(Category category) {
		find(category.getId());
		return categoryRepository.save(category);
	}

	public void delete(Integer id) {
		find(id);
		try {
		categoryRepository.deleteById(id);
		}catch (DataIntegrityViolationException e) {//Exception launched when try to delete a category with products
			throw new DataIntegrityException("It is no possible delete a category with products.");
		}
	}
	
	
}
