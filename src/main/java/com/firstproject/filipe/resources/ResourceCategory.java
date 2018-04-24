package com.firstproject.filipe.resources;


import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.firstproject.filipe.domain.Category;


//The annotation declare that this is a Rest Controller
@RestController
@RequestMapping(value="/categories")
public class ResourceCategory {
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Category> lista() {
		
		Category category1 = new Category(1, "Computing");
		Category category2 = new Category(2, "Office");
		
		List<Category> categories = new ArrayList<>();
		categories.add(category1);
		categories.add(category2);
		
		
		
		return categories;
		
	}

}
