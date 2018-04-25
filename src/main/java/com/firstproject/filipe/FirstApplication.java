package com.firstproject.filipe;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.firstproject.filipe.domain.Category;
import com.firstproject.filipe.repositories.CategoryRepository;

@SpringBootApplication
public class FirstApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(FirstApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		
//		Category cat1 =  new Category(null, "Computer");
//		Category cat2 = new Category(null, "Office");
//		
//		categoryRepository.saveAll(Arrays.asList(cat1,cat2));
		
	}
}
