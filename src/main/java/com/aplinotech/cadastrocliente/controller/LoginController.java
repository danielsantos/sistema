package com.aplinotech.cadastrocliente.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.aplinotech.cadastrocliente.util.Routes;

@Controller
public class LoginController {
	
	private static final String LOGIN_VIEW = "login/login";
	
	@RequestMapping(value = "/login")
	public ModelAndView autenticar(@AuthenticationPrincipal User user){
		if (user != null) {
			return new ModelAndView(Routes.USERS);
		}
		return new ModelAndView(LOGIN_VIEW);
	}
	
}
