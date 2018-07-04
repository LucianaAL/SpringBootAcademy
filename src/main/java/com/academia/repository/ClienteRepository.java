package com.academia.repository;

import org.springframework.data.repository.CrudRepository;

import com.academia.model.Academia;
import com.academia.model.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, String>{
	
	Iterable<Cliente> findByAcademia(Academia academia);
	
	Cliente findByMatricula(long matricula);
	
}
