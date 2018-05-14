package com.firstproject.filipe.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.firstproject.filipe.domain.Category;
import com.firstproject.filipe.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

//	@Query("SELECT DISTINCT obj FROM Product obj INNER JOIN obj.categories cat"
//			+ " where obj.name like %:name% AND cat IN :categories")
//	Page<Product> search(@Param("name") String name, @Param("categories") List<Category> categories, Pageable pageRequest);
	@Transactional(readOnly=true)
	Page<Product> findDistinctByNameContainingAndCategoriesIn(String name, List<Category> categories, Pageable pageRequest);
	
	

}
