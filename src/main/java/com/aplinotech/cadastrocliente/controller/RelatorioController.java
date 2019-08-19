package com.aplinotech.cadastrocliente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.aplinotech.cadastrocliente.model.dto.RelatorioDTO;
import com.aplinotech.cadastrocliente.service.RelatorioService;

@Controller
@RequestMapping("/relatorio")
public class RelatorioController {
	
	@Autowired
	private RelatorioService relatorioService;
	

	@RequestMapping("/entrada")
	public ModelAndView entrada() {
		return relatorioService.entrada();
	}
	
	@RequestMapping("/estoque")
	public ModelAndView estoque() {
		return relatorioService.estoque();
	}
	
	@RequestMapping("/saida")
	public ModelAndView saida() {
		return relatorioService.saida();
	}
	
	@RequestMapping("/entrada/gerar")
	public ModelAndView entradaGerar(@ModelAttribute("dto") RelatorioDTO dto) {
		return relatorioService.entradaGerar(dto);
	}
	
	@RequestMapping("/saida/gerar")
	public ModelAndView saidaGerar(@ModelAttribute("dto") RelatorioDTO dto) {
		return relatorioService.saidaGerar(dto);
	}
	
	@RequestMapping("/estoque/gerar")
	public ModelAndView estoqueGerar() {
		return relatorioService.estoqueGerar();
	}
	
}
