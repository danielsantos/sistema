package com.aplinotech.cadastrocliente.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aplinotech.cadastrocliente.model.Setup;
import com.aplinotech.cadastrocliente.service.impl.SetupServiceImpl;

@Controller
public class LoginController {
	
	//private static final String LOGIN_VIEW = "login/login";
	
	@Autowired
	private SetupServiceImpl setupServiceImpl;
	
	@RequestMapping(value = "/login")
	public String autenticar(@AuthenticationPrincipal User user){
		if (user != null) {
			
			Setup setup = setupServiceImpl.find();
			
			if ( setup == null ) {

				Calendar cal = new GregorianCalendar();
				cal.add(Calendar.DATE, -30);
				
				Setup configuracaoSistema = new Setup();
				configuracaoSistema.setDataPrimeiroAcesso(new Date());
				configuracaoSistema.setDataExpiracao(cal.getTime());
				
				setupServiceImpl.saveOrUpdate(configuracaoSistema);
				
			} else {
				
				if ( setup.getDataExpiracao().before(new Date()) ) {
					
					if ( setup.getCodigoAtivacao() == null ) {
					
						return "login/expirado";
						
					} else if ( !setup.getCodigoAtivacao().equals("890531") ) {
						
						return "login/expirado";
						
					}
					
				} 
				
			}
			
			return "produto/baixa";
			
		}
		
		return "login/login";
		
	}
	
}
