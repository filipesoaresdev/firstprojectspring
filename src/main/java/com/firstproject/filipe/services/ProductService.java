package com.firstproject.filipe.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.firstproject.filipe.domain.Category;
import com.firstproject.filipe.domain.Product;
import com.firstproject.filipe.repositories.CategoryRepository;
import com.firstproject.filipe.repositories.ProductRepository;
import com.firstproject.filipe.services.exceptions.ObjectNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repo;
	@Autowired
	private CategoryRepository categoryRepository;
	
	public Product find(Integer id) {
		Optional<Product> obj = repo.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException(""
						+ "Object Not Found. Id: "+ id+ " Type: "+ Product.class.getName()));
	}
	
	public Page<Product> search(
			String name, 
			List<Integer> ids,
			Integer page,
			Integer linesPerPage,
			String orderBy,
			String direction){
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Category> categories = categoryRepository.findAllById(ids);
		
		return repo.findDistinctByNameContainingAndCategoriesIn(name,categories,pageRequest);
	}
	
	
}
