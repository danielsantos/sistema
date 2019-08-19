package com.aplinotech.cadastrocliente.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.aplinotech.cadastrocliente.model.Produto;
import com.aplinotech.cadastrocliente.model.dto.PesquisarProdutoDTO;
import com.aplinotech.cadastrocliente.service.impl.EstoqueServiceImpl;

@Controller
@RequestMapping("/estoque") 
public class EstoqueController {
	
	@Autowired
	private EstoqueServiceImpl estoqueServiceImpl;
	
	
	@RequestMapping(value = "/entrada")
	public ModelAndView entrada(){
		return estoqueServiceImpl.entrada();
	}
	
	@RequestMapping(value = "/entrada/pesquisar", method = RequestMethod.POST)
	public ModelAndView entradaPesquisar(@ModelAttribute("dto") PesquisarProdutoDTO dto) {
		return estoqueServiceImpl.entradaPesquisar(dto);
	}
	
	@RequestMapping(value = "/entrada/salvar", method = RequestMethod.POST)
	public ModelAndView entradaSalvar(@ModelAttribute(value = "produto") Produto produto) {
		return estoqueServiceImpl.entradaSalvar(produto);
	}
	
	@RequestMapping(value = "/removeProdutoBaixa/{id}", method = RequestMethod.GET)
	public ModelAndView removeProdutoBaixa(@PathVariable(value = "id") Long id, HttpSession session) {
		return estoqueServiceImpl.removeProdutoBaixa(id, session);
	}
	
	@RequestMapping(value = "/registrarBaixa", method = RequestMethod.GET)
	public ModelAndView registrarBaixa(HttpSession session) {
		return estoqueServiceImpl.registrarBaixa(session);
	}
	
	@RequestMapping(value = "/baixa/add", method = RequestMethod.POST)
	public String baixaAddProd(@ModelAttribute("produto") Produto produto, ModelMap modelMap, HttpSession session) {
		return estoqueServiceImpl.baixaAddProd(produto, modelMap, session);
	}
	
	@RequestMapping(value = "/pesquisar", method = RequestMethod.POST)
	public String baixa(@ModelAttribute("dto") PesquisarProdutoDTO dto, ModelMap modelMap, HttpSession session) {
		return estoqueServiceImpl.baixa(modelMap, session);
	}
	
	@RequestMapping("/retorna/produto/{codigo}") 
	public String retornaProdutoPesquisadoPorNome(@PathVariable(value = "codigo") String codigo, ModelMap modelMap, HttpSession session) {
		return estoqueServiceImpl.retornaProdutoPesquisadoPorNome(codigo, modelMap, session);
	}
	
	@RequestMapping(value = "/baixa", method = RequestMethod.GET)
	public String baixa(ModelMap modelMap, HttpSession session) {
		return estoqueServiceImpl.baixa(modelMap, session);
	}
	
	@RequestMapping(value = "/consultar/produto/nome/form")
	public ModelAndView consultaProdutoForm() {
		return consultaProdutoForm();
	}
	
	@RequestMapping(value = "/consultar/produto/nome", method = RequestMethod.POST)
	public String consultaProduto(@ModelAttribute("dto") PesquisarProdutoDTO dto, ModelMap modelMap, HttpSession session) {
		return consultaProduto(dto, modelMap, session);
	}

}
