package com.aplinotech.cadastrocliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aplinotech.cadastrocliente.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

    @Query(value="SELECT p FROM Produto p WHERE p.codigo = :codigo and p.status = 'A'")
    Produto findByCodigoAndActive(@Param("codigo") String codigo);
	
}
