package com.aplinotech.cadastrocliente.service;

import java.util.List;

import com.aplinotech.cadastrocliente.model.Baixa;

public interface BaixaService {

    void saveOrUpdate(Baixa baixa);
 
    void deleteLogic(Long id);
    
    Baixa findById(Long id);
 
    List<Baixa> findAll();
 
}
