package com.aplinotech.cadastrocliente.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aplinotech.cadastrocliente.model.Setup;
import com.aplinotech.cadastrocliente.repository.SetupRepository;
import com.aplinotech.cadastrocliente.service.SetupService;

@Service
@Transactional
public class SetupServiceImpl implements SetupService {
	
	@Autowired
	private SetupRepository configuracaoSistemaRepository;

	@Override
	public void saveOrUpdate(Setup configuracaoSistema) {
		configuracaoSistemaRepository.save(configuracaoSistema);
	}

	@Override
	public Setup find() {
		List<Setup> list = configuracaoSistemaRepository.findAll();
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

}