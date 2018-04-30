package com.aplinotech.cadastrocliente.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.aplinotech.cadastrocliente.model.Entrada;
import com.aplinotech.cadastrocliente.model.dto.RelatorioDTO;
import com.aplinotech.cadastrocliente.service.impl.EntradaServiceImpl;

@Controller
@RequestMapping("/relatorio")
public class RelatorioController {
	
	@Autowired
	private EntradaServiceImpl entradaServiceImpl;

	@RequestMapping("/entrada")
	public ModelAndView entrada(){
		ModelAndView mv = new ModelAndView("relatorio/entrada");
		mv.addObject("dto", new RelatorioDTO());
		return mv;
	}
	
	@RequestMapping("/entrada/gerar")
	public ModelAndView entradaGerar(@ModelAttribute("dto") RelatorioDTO dto){
		ModelAndView mv = new ModelAndView("relatorio/entradarel");
		
		List<Entrada> list = entradaServiceImpl.findByDates(new Date(), new Date());
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		BigDecimal custoUnitarioTotal = new BigDecimal(0);
		BigDecimal valorVendaUnitarioTotal = new BigDecimal(0);
		
		for (Entrada e : list) {
			e.setDataFormatada(sdf.format(e.getData()));
			custoUnitarioTotal = e.getCustoUnitarioTotal().add(custoUnitarioTotal);
			valorVendaUnitarioTotal = e.getValorVendaUnitarioTotal().add(valorVendaUnitarioTotal);
		}
		
		mv.addObject("list", list);
		mv.addObject("custoUnitarioTotal", custoUnitarioTotal);
		mv.addObject("valorVendaUnitarioTotal", valorVendaUnitarioTotal);
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
