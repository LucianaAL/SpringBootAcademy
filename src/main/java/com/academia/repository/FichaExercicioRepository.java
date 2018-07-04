package com.academia.repository;

import org.springframework.data.repository.CrudRepository;

import com.academia.model.FichaExercicio;

public interface FichaExercicioRepository extends CrudRepository<FichaExercicio, String>{
	
	FichaExercicio findById(long id);

}
