package com.aplinotech.cadastrocliente.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aplinotech.cadastrocliente.model.Produto;
import com.aplinotech.cadastrocliente.repository.ProdutoRepository;
import com.aplinotech.cadastrocliente.service.ProdutoService;

@Service
@Transactional
public class ProdutoServiceImpl implements ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@Override
	public void saveOrUpdate(Produto produto) {
		produtoRepository.save(produto);
	}

	@Override
	public void deleteLogic(Long id) {
		Produto produto = produtoRepository.findOne(id);
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

}
