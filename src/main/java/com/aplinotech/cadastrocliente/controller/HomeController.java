package com.aplinotech.cadastrocliente.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aplinotech.cadastrocliente.model.Produto;
import com.aplinotech.cadastrocliente.model.dto.PesquisarProdutoDTO;
import com.aplinotech.cadastrocliente.service.impl.ProdutoServiceImpl;

@Controller
public class HomeController {
	
	@Autowired
	private ProdutoServiceImpl produtoServiceImpl;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String baixa(ModelMap modelMap, HttpSession session) {
		
		List<Produto> list = new ArrayList<Produto>();
		
		if ( session.getAttribute("produtosBaixa") == null ) {
			
			session.setAttribute("produtosBaixa", new ArrayList<Produto>());
			
		} else {
			
			list = (List<Produto>) session.getAttribute("produtosBaixa");
			
		}
		
		modelMap.addAttribute("produtos", produtoServiceImpl.findAll());
		modelMap.addAttribute("produtosBaixa", list);
		modelMap.addAttribute("dto", new PesquisarProdutoDTO());
		modelMap.addAttribute("produto", new Produto());
		return "produto/baixa";
	}

}
