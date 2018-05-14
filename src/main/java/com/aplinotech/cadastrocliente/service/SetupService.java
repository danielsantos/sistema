package com.aplinotech.cadastrocliente.service;

import com.aplinotech.cadastrocliente.model.Setup;

public interface SetupService {

    void saveOrUpdate(Setup configuracaoSistema);
 
    Setup find();
    
    boolean sistemaExpirou();
 
}
