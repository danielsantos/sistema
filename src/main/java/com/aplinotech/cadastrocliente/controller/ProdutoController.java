package com.aplinotech.cadastrocliente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.aplinotech.cadastrocliente.model.Produto;
import com.aplinotech.cadastrocliente.model.dto.PesquisarProdutoDTO;
import com.aplinotech.cadastrocliente.service.impl.ProdutoServiceImpl;


@Controller
@RequestMapping("/produto") 
public class ProdutoController {	
	
	@Autowired
	private ProdutoServiceImpl produtoServiceImpl;
	
	@RequestMapping(value = "/salvar/form", method = RequestMethod.GET)
	public ModelAndView salvarForm(){
		return produtoServiceImpl.salvarForm();
	}
	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public ModelAndView salvar(@ModelAttribute(value = "produto") Produto produto, Errors errors, ModelMap modelMap){
		return produtoServiceImpl.salvar(produto, errors, modelMap);
	}
	
	@RequestMapping(value = "/atualizar/form/{codigo}", method = RequestMethod.GET)
	public ModelAndView altualizarForm(@PathVariable(value = "codigo") String codigo){
		return produtoServiceImpl.atualizarForm(codigo);
	}
	
	@RequestMapping(value = "/atualizar", method = RequestMethod.POST)
	public ModelAndView atualizar(@ModelAttribute(value = "produto") Produto produto, Errors errors, ModelMap modelMap) {
		return produtoServiceImpl.atualizar(produto, errors, modelMap);
	}
	
	@RequestMapping(value = "/excluir/{codigo}", method =RequestMethod.GET)
	public String excluir(@PathVariable(value = "codigo") String codigo, ModelMap modelMap){
		return produtoServiceImpl.excluir(codigo);
	}
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(ModelMap modelMap) {
		return produtoServiceImpl.listar(modelMap);
	}
	
	@RequestMapping(value = "/visualizar/{codigo}", method = RequestMethod.GET)
	public String visualizar(@PathVariable(value = "codigo") String codigo, ModelMap modelMap) {
		return visualizar(codigo, modelMap);
	}	
	
	@RequestMapping(value = "/consultar", method = RequestMethod.POST)
	public String consultar(@ModelAttribute("dto") PesquisarProdutoDTO dto, ModelMap modelMap) {
		return consultar(dto, modelMap);
	}

}
