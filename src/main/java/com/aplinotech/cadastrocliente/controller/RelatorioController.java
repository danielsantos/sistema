package com.aplinotech.cadastrocliente.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.aplinotech.cadastrocliente.model.dto.RelatorioDTO;

@Controller
@RequestMapping("/relatorio")
public class RelatorioController {

	@RequestMapping("/entrada")
	public ModelAndView entrada(){
		ModelAndView mv = new ModelAndView("relatorio/entrada");
		mv.addObject("dto", new RelatorioDTO());
		return mv;
	}
	
	@RequestMapping("/entrada/gerar")
	public ModelAndView entradaGerar(@ModelAttribute("dto") RelatorioDTO dto){
		ModelAndView mv = new ModelAndView("relatorio/entradarel");
		mv.addObject("data", new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()));
		return mv;
	}
	
	@RequestMapping("/saida")
	public ModelAndView saida(){
		ModelAndView mv = new ModelAndView("relatorio/saida");
		return mv;
	}

	@RequestMapping("/estoque")
	public ModelAndView estoque(){
		ModelAndView mv = new ModelAndView("relatorio/estoque");
		return mv;
	}
	
}
