package com.firstproject.filipe.services;
import java.util.List;
/**
 * @author Filipe
 * 
 */
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.firstproject.filipe.domain.Category;
import com.firstproject.filipe.dto.CategoryDTO;
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

	//Update a Category
	public Category update(Category category) {
		find(category.getId());
		return categoryRepository.save(category);
	}

	//delete a category by Id
	public void delete(Integer id) {
		find(id);
		try {
		categoryRepository.deleteById(id);
		}catch (DataIntegrityViolationException e) {//Exception launched when try to delete a category with products
			throw new DataIntegrityException("It is no possible delete a category with products.");
		}
	}

	//List of categories
	public List<Category> findAll() {
		
		return categoryRepository.findAll();
	}
	
	public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage,Direction.valueOf(direction),orderBy);
		return categoryRepository.findAll(pageRequest);
	}
	
	public Category fromDTO(CategoryDTO categoryDTO) {
		return new Category(categoryDTO.getId(),categoryDTO.getName());
	}
	
}
