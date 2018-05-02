package com.aplinotech.cadastrocliente.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aplinotech.cadastrocliente.model.Setup;
import com.aplinotech.cadastrocliente.service.impl.SetupServiceImpl;

@Controller
public class LoginController {
	
	private static final String CODE_ACTIVE = "890531";
	
	@Autowired
	private SetupServiceImpl setupServiceImpl;

	@RequestMapping(value = "/login")
	public String login(@AuthenticationPrincipal User user) {
		return "login/login";
	}
	
	@RequestMapping(value = "/expired")
	public String process(@AuthenticationPrincipal User user, HttpSession session) {
		
		if ( user != null ) {
			
			Setup setup = setupServiceImpl.find();
			
			if ( setup.getDataExpiracao().before(new Date()) ) {
				
				if ( setup.getCodigoAtivacao() == null ) {
					
					SecurityContextHolder.clearContext();
					return "login/expirado";
					
				} else if ( !setup.getCodigoAtivacao().equals(CODE_ACTIVE) ) {

					SecurityContextHolder.clearContext();
					return "login/expirado";
					
				}
				
			} 
			
		}
		
		return null;
	}
	
}
