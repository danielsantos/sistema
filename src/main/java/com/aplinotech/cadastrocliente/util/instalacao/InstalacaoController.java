package com.aplinotech.cadastrocliente.util.instalacao;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InstalacaoController {
	
	@Autowired
	private InstalacaoRepository repository;

	@RequestMapping(value = "/instala")
	public ModelAndView instala() {
		
		Calendar cal = new GregorianCalendar();
		cal.add(Calendar.DATE, 30);
		
		repository.insereSetup(cal.getTime());
		repository.insereUsuario();
		repository.insereRole();
		repository.insereUsuarioRole();
		
		ModelAndView mv = new ModelAndView("util/home");
		mv.addObject("mensagem", "Instalação Concluída com Sucesso");
		return mv;
		
	}
	
}
