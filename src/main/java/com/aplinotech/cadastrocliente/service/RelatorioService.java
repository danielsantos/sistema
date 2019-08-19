package com.aplinotech.cadastrocliente.service;

import org.springframework.web.servlet.ModelAndView;

import com.aplinotech.cadastrocliente.model.dto.RelatorioDTO;

public interface RelatorioService {

	ModelAndView entrada();
	
	ModelAndView estoque();
	
	ModelAndView saida();
	
	ModelAndView entradaGerar(RelatorioDTO dto);
	
	ModelAndView saidaGerar(RelatorioDTO dto);
	
	ModelAndView estoqueGerar();
	
}
