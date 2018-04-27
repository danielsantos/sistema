package com.aplinotech.cadastrocliente.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.LongFunction;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.aplinotech.cadastrocliente.model.Baixa;
import com.aplinotech.cadastrocliente.model.ItemBaixa;
import com.aplinotech.cadastrocliente.model.Produto;
import com.aplinotech.cadastrocliente.model.dto.PesquisarProdutoDTO;
import com.aplinotech.cadastrocliente.service.impl.BaixaServiceImpl;
import com.aplinotech.cadastrocliente.service.impl.ItemBaixaServiceImpl;
import com.aplinotech.cadastrocliente.service.impl.ProdutoServiceImpl;


@Controller
@RequestMapping("/produto") 
public class ProdutoController {	
	
	@Autowired
	private ProdutoServiceImpl produtoServiceImpl;
	
	@Autowired
	private BaixaServiceImpl baixaServiceImpl;

	@Autowired
	private ItemBaixaServiceImpl itemBaixaServiceImpl;

	
	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public ModelAndView novo(){
		ModelAndView mv = new ModelAndView("produto/novo");
		mv.addObject("produto", new Produto());
		return mv;
	}
	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public ModelAndView salvar(@ModelAttribute(value = "produto") Produto produto, Errors errors, ModelMap modelMap){
		ModelAndView mv = new ModelAndView("produto/novo");
		modelMap.addAttribute("produto", produto);
		if(errors.hasErrors()){
			return mv;
		} else {
			produtoServiceImpl.saveOrUpdate(produto);
			mv.addObject("mensagem", "Salvo com sucesso!");
		}
		return mv;
	}
	
	@RequestMapping(value = "/atualizar/{id}", method = RequestMethod.GET)
	public ModelAndView alterar(@PathVariable(value = "id") Long id){
		ModelAndView mv = new ModelAndView("produto/atualizar");
		mv.addObject("produto", produtoServiceImpl.findById(id));
		return mv;
	}
	
	@RequestMapping(value = "/alterar", method = RequestMethod.POST)
	public ModelAndView atualizar(@ModelAttribute(value = "produto") Produto produto, Errors errors, ModelMap modelMap){
		ModelAndView mv = new ModelAndView("produto/atualizar");
		modelMap.addAttribute("produto", produto);
		if(errors.hasErrors()){
			return mv;
		} else {
			produtoServiceImpl.saveOrUpdate(produto);
			mv.addObject("mensagem", "Dados atualizados com sucesso!");
		}
		return mv;
	}
	
	@RequestMapping(value = "/excluir/{id}", method =RequestMethod.GET)
	public String excluir(@PathVariable(value = "id") Long id, ModelMap modelMap){
		produtoServiceImpl.deleteLogic(id);
		return "redirect:/produto/listar";
	}
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(ModelMap modelMap) {
		List<Produto> produtos = produtoServiceImpl.findAll();
		modelMap.addAttribute("produtos", produtos);
		return "produto/listar";
	}
	
	@RequestMapping(value = "/visualizar/{id}", method = RequestMethod.GET)
	public String visualizar(@PathVariable(value = "id") Long id, ModelMap modelMap){
		modelMap.addAttribute("produto", produtoServiceImpl.findById(id));
		return "produto/visualizar";
	}	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/baixa", method = RequestMethod.GET)
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
		return "produto/baixa";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/pesquisar", method = RequestMethod.POST)
	public String baixa(@ModelAttribute("dto") PesquisarProdutoDTO dto, ModelMap modelMap, HttpSession session) {
		
		List<Produto> list = new ArrayList<Produto>();
		
		if ( session.getAttribute("produtosBaixa") == null ) {
			session.setAttribute("produtosBaixa", new ArrayList<Produto>());
		} else {
			list = (List<Produto>) session.getAttribute("produtosBaixa");
		}
		
		Produto produto = produtoServiceImpl.findById(new Long(dto.getCodigoProduto()));
		
		modelMap.addAttribute("produtos", produtoServiceImpl.findAll());
		modelMap.addAttribute("produtosBaixa", list);
		modelMap.addAttribute("dto", new PesquisarProdutoDTO());
		modelMap.addAttribute("produto", produto);
		return "produto/baixa";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/baixa/add", method = RequestMethod.GET)
	public String baixaAddProd(@ModelAttribute("produto") Produto produto, ModelMap modelMap, HttpSession session) {
		
		List<Produto> list = new ArrayList<Produto>();
		
		if ( session.getAttribute("produtosBaixa") == null ) {
			
			session.setAttribute("produtosBaixa", new ArrayList<Produto>());
			
		} else {
			
			list = (List<Produto>) session.getAttribute("produtosBaixa");
			//Produto produto = produtoServiceImpl.findById(/*id*/1l);
			list.add(produto);
			
		}
		
		modelMap.addAttribute("produtos", produtoServiceImpl.findAll());
		modelMap.addAttribute("produtosBaixa", list);
		modelMap.addAttribute("produto", new Produto());
		return "produto/baixa";
	}
		
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/registrarBaixa", method = RequestMethod.GET)
	public ModelAndView registrarBaixa(HttpSession session) {
		
		if ( session.getAttribute("produtosBaixa") != null ) {
			
			List<Produto> listProdutoBaixa = (List<Produto>) session.getAttribute("produtosBaixa");
			
			Baixa baixa = new Baixa();
			
			baixaServiceImpl.saveOrUpdate(baixa);
			
			for ( Produto produto : listProdutoBaixa ) {
				
				ItemBaixa item = new ItemBaixa();
				item.setProduto(produto);
				item.setQuantidade(10);
				item.setBaixa(baixa);
				item.setValorUnitario(new BigDecimal(1));
				
				itemBaixaServiceImpl.saveOrUpdate(item);
				
			}

			session.setAttribute("produtosBaixa", null);
			
		}
		
		ModelAndView mv = new ModelAndView("produto/baixa");
		mv.addObject("mensagem", "Baixa efetuada com sucesso!");
		mv.addObject("produtos", produtoServiceImpl.findAll());
		return mv;
		
	}


	/*
	@RequestMapping(value = Routes.USER_PESQUISAR, method = RequestMethod.GET)
	public String pesquisar(@ModelAttribute(value = "name") String name, ModelMap modelMap){
		List<Usuario> users = userServiceImpl.findByName(name);
		modelMap.addAttribute("users", users);
		return Views.LISTAR;
	}
	*/

}
