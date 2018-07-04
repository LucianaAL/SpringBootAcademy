package com.academia.repository;

import org.springframework.data.repository.CrudRepository;

import com.academia.model.Academia;

public interface AcademiaRepository extends CrudRepository<Academia, String>{
	
		
	Academia findById(long id);
	
}
