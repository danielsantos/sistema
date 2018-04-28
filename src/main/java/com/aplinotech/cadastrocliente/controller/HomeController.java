package com.aplinotech.cadastrocliente.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aplinotech.cadastrocliente.model.Baixa;
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
		Baixa baixa = new Baixa();
		
		if ( session.getAttribute("baixa") == null ) {

			session.setAttribute("baixa", baixa);
			
		} else {
			
			baixa = (Baixa) session.getAttribute("baixa");
			BigDecimal total = BigDecimal.ZERO;
			if ( baixa.getProdutos() != null && !baixa.getProdutos().isEmpty() ) {
				for (Produto p : list) {
					total = p.getValorTotal().add(total);
				}
			}
			
			baixa.setValorTotal(total);
			session.setAttribute("baixa", baixa);
			
		}
		
		modelMap.addAttribute("produtosBaixa", list);
		modelMap.addAttribute("dto", new PesquisarProdutoDTO());
		modelMap.addAttribute("produto", new Produto());
		modelMap.addAttribute("baixa", baixa);
		return "produto/baixa";	
	}

}
