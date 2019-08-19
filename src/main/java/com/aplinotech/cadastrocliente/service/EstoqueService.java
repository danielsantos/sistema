package com.aplinotech.cadastrocliente.service;

import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.aplinotech.cadastrocliente.model.Produto;
import com.aplinotech.cadastrocliente.model.dto.PesquisarProdutoDTO;

public interface EstoqueService {

	ModelAndView entrada();
	
	ModelAndView entradaPesquisar(PesquisarProdutoDTO dto);
	
	ModelAndView entradaSalvar(Produto produto);
	
	ModelAndView removeProdutoBaixa(Long id, HttpSession session);
	
	ModelAndView registrarBaixa(HttpSession session);
	
	String baixaAddProd(Produto produto, ModelMap modelMap, HttpSession session);
	
	String baixa(PesquisarProdutoDTO dto, ModelMap modelMap, HttpSession session);
	
	String retornaProdutoPesquisadoPorNome(String codigo, ModelMap modelMap, HttpSession session);
	
	String baixa(ModelMap modelMap, HttpSession session);
	
	ModelAndView consultaProdutoForm();
	
	String consultaProduto(PesquisarProdutoDTO dto, ModelMap modelMap, HttpSession session);
	
}
