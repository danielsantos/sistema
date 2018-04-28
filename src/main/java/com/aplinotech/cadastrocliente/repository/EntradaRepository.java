package com.aplinotech.cadastrocliente.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.aplinotech.cadastrocliente.model.Entrada;

@Repository
public interface EntradaRepository extends JpaRepository<Entrada, Long>{

    @Query(value="SELECT e FROM Entrada e")
    List<Entrada> findByDates();
	
}
