package com.aplinotech.cadastrocliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aplinotech.cadastrocliente.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
