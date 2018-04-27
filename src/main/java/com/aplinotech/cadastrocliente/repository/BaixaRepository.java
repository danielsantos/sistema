package com.aplinotech.cadastrocliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aplinotech.cadastrocliente.model.Baixa;

@Repository
public interface BaixaRepository extends JpaRepository<Baixa, Long> {

}
