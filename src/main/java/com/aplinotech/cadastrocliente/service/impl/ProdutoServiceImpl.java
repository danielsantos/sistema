package com.aplinotech.cadastrocliente.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.aplinotech.cadastrocliente.model.Produto;
import com.aplinotech.cadastrocliente.model.dto.PesquisarProdutoDTO;
import com.aplinotech.cadastrocliente.repository.ProdutoRepository;
import com.aplinotech.cadastrocliente.service.ProdutoService;

@Service
@Transactional
public class ProdutoServiceImpl implements ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private SetupServiceImpl setupServiceImpl;

	@Override
	public void saveOrUpdate(Produto produto) {
		produtoRepository.save(produto);
	}

	@Override
	public void deleteLogic(String codigo) {
		Produto produto = produtoRepository.findByCodigoAndActive(codigo);
		produto.setStatus("I");
		saveOrUpdate(produto);
	}

	@Override
	public Produto findById(Long id) {
		return produtoRepository.findOne(id);
	}
	
	@Override
	public Produto findByCodigoAndActive(String codigo) {
		return produtoRepository.findByCodigoAndActive(codigo);
	}

	@Override
	public List<Produto> findAll() {
		return produtoRepository.findAll();
	}
	
	@Override
	public List<Produto> findAllActive() {
		return produtoRepository.findAllActive();
	}
	
	@Override
	public List<Produto> findByNome(String nome) {
		return produtoRepository.findByNome(nome);
	}
	
	@Override
	public Produto findByCodigo(String codigo) {
		return produtoRepository.findByCodigo(codigo);
	}
	
	public ModelAndView salvarForm() {
		if (setupServiceImpl.sistemaExpirou()) 
			return new ModelAndView("login/expirado");
		
		ModelAndView mv = new ModelAndView("produto/novo");
		mv.addObject("produto", new Produto());
		return mv;
	}
	
	public ModelAndView salvar(Produto produto, Errors errors, ModelMap modelMap) {
		if (setupServiceImpl.sistemaExpirou()) 
			return new ModelAndView("login/expirado");
		
		ModelAndView mv = new ModelAndView("produto/novo");
		
		if (produto.getCodigo() == null || "".equals(produto.getCodigo())) {
			modelMap.addAttribute("produto", produto);
			modelMap.addAttribute("msgError", "O campo Código é obrigatório.");
			return mv;
		}
		
		Produto prod = findByCodigo(produto.getCodigo());
		if (prod != null) {
			modelMap.addAttribute("produto", produto);
			modelMap.addAttribute("msgError", "O Código '" + produto.getCodigo() + "' já está cadastrado para outro Produto.");
			return mv;
		}
		
		if(errors.hasErrors()){
			modelMap.addAttribute("produto", produto);
			return mv;
		} else {
			produto.setStatus("A"); // TODO criar um enum
			saveOrUpdate(produto);
			mv.addObject("mensagem", "Produto cadastrado com sucesso!");
			modelMap.addAttribute("produto", new Produto());
		}
		return mv;
	}
	
	public ModelAndView atualizarForm(String codigo) {
		if (setupServiceImpl.sistemaExpirou()) 
			return new ModelAndView("login/expirado");
		
		ModelAndView mv = new ModelAndView("produto/atualizar");
		mv.addObject("produto", findByCodigoAndActive(codigo));
		return mv;
	}
	
	public ModelAndView atualizar(Produto produto, Errors errors, ModelMap modelMap) {
		if (setupServiceImpl.sistemaExpirou()) 
			return new ModelAndView("login/expirado");
		
		ModelAndView mv = new ModelAndView("produto/atualizar");
		modelMap.addAttribute("produto", produto);
		
		if(errors.hasErrors()){
			return mv;
		} else {
			saveOrUpdate(produto);
			mv.addObject("mensagem", "Dados atualizados com sucesso!");
		}
		
		return mv;
	}
	
	public String excluir(String codigo) {
		if (setupServiceImpl.sistemaExpirou()) 
			return "redirect:/login/expirado";
		
		deleteLogic(codigo);
		return "redirect:/produto/listar";
	}
	
	public String listar(ModelMap modelMap) {
		if (setupServiceImpl.sistemaExpirou()) 
			return "login/expirado";
		
		modelMap.addAttribute("produtos", findAllActive());
		modelMap.addAttribute("dto", new PesquisarProdutoDTO());
		return "produto/listar";
	}
	
	public String visualizar(String codigo, ModelMap modelMap) {
		if (setupServiceImpl.sistemaExpirou()) 
			return "login/expirado";
		
		modelMap.addAttribute("produto", findByCodigoAndActive(codigo));
		return "produto/visualizar";
	}
	
	public String consultar(PesquisarProdutoDTO dto, ModelMap modelMap) {
		if (setupServiceImpl.sistemaExpirou()) 
			return "login/expirado";
		
		if ( !"".equals(dto.getNome()) ) {
			
			modelMap.addAttribute("produtos", findByNome(dto.getNome()));
			
		} else if ( !"".equals(dto.getCodigoProduto()) ) {
			
			List<Produto> produtos = new ArrayList<Produto>();
			Produto produto = findByCodigo(dto.getCodigoProduto());
			
			if (produto != null)
				produtos.add(produto);
			
			modelMap.addAttribute("produtos", produtos);
			
		} else { 
			modelMap.addAttribute("produtos", findAllActive());
		}
			
	    modelMap.addAttribute("dto", new PesquisarProdutoDTO());		
		return "produto/listar";
	}

}
