package com.aplinotech.cadastrocliente.service;

import java.util.List;

import com.aplinotech.cadastrocliente.model.Produto;

public interface ProdutoService {

    void saveOrUpdate(Produto produto);
 
    void deleteLogic(Long id);
    
    Produto findById(Long id);
    
    Produto findByCodigoAndActive(String codigo);
 
    List<Produto> findAll();
 
}
