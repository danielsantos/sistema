package com.aplinotech.cadastrocliente.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aplinotech.cadastrocliente.model.Usuario;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {

    @Query(value="SELECT u FROM Usuario u WHERE u.username = :username and u.status = 'A'")
    Usuario findByUsernameAndActive(@Param("username") String username);
	
	List<Usuario> findByNome(String nome);
	
}
