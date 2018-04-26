package com.aplinotech.cadastrocliente.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aplinotech.cadastrocliente.model.Usuario;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long>{

	List<Usuario> findByName(String name);
	
}
