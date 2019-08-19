package com.aplinotech.cadastrocliente.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.aplinotech.cadastrocliente.model.Entrada;
import com.aplinotech.cadastrocliente.model.ItemBaixa;
import com.aplinotech.cadastrocliente.model.Produto;
import com.aplinotech.cadastrocliente.model.dto.RelatorioDTO;
import com.aplinotech.cadastrocliente.service.RelatorioService;

@Service
@Transactional
public class RelatorioServiceImpl implements RelatorioService {
	
	@Autowired
	private EntradaServiceImpl entradaServiceImpl;
	
	@Autowired
	private BaixaServiceImpl baixaServiceImpl;
	
	@Autowired
	private ProdutoServiceImpl produtoServiceImpl;
	
	@Autowired
	private SetupServiceImpl setupServiceImpl;

	@Override
	public ModelAndView entrada() {
		
		if (setupServiceImpl.sistemaExpirou()) 
			return new ModelAndView("login/expirado");
		
		ModelAndView mv = new ModelAndView("relatorio/entrada");
		mv.addObject("dto", new RelatorioDTO());
		return mv;
	}

	@Override
	public ModelAndView estoque() {
		
		if (setupServiceImpl.sistemaExpirou()) 
			return new ModelAndView("login/expirado");
		
		ModelAndView mv = new ModelAndView("relatorio/estoque");
		return mv;
	}
	
	@Override
	public ModelAndView saida() {
		
		if (setupServiceImpl.sistemaExpirou()) 
			return new ModelAndView("login/expirado");
		
		ModelAndView mv = new ModelAndView("relatorio/saida");
		mv.addObject("dto", new RelatorioDTO());
		return mv;
	}
	
	
	@Override
	public ModelAndView entradaGerar(RelatorioDTO dto) {
		
		if (setupServiceImpl.sistemaExpirou()) 
			return new ModelAndView("login/expirado");
		
		ModelAndView mv = new ModelAndView("relatorio/entradarel");
		
		SimpleDateFormat sdfBD = new SimpleDateFormat("dd/MM/yyyy");
		List<Entrada> list = new ArrayList<Entrada>();
		
		try {
			list = entradaServiceImpl.findByDates(sdfBD.parse(dto.getDataInicio()), sdfBD.parse(dto.getDataFim()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		BigDecimal custoUnitarioTotal = new BigDecimal(0);
		BigDecimal valorVendaUnitarioTotal = new BigDecimal(0);
		
		for (Entrada e : list) {
			e.setDataFormatada(sdf.format(e.getData()));
			custoUnitarioTotal = e.getCustoUnitarioTotal().add(custoUnitarioTotal);
			valorVendaUnitarioTotal = e.getValorVendaUnitarioTotal().add(valorVendaUnitarioTotal);
		}
		
		mv.addObject("list", list);
		mv.addObject("custoUnitarioTotal", custoUnitarioTotal);
		mv.addObject("valorVendaUnitarioTotal", valorVendaUnitarioTotal);
		mv.addObject("data", new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()));
		
		return mv;
	}
	
	@Override
	public ModelAndView saidaGerar(RelatorioDTO dto) {
		
		if (setupServiceImpl.sistemaExpirou()) 
			return new ModelAndView("login/expirado");
		
		ModelAndView mv = new ModelAndView("relatorio/saidarel");
		
		SimpleDateFormat sdfBD = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		List<ItemBaixa> list = new ArrayList<ItemBaixa>();
		
		try {
			list = baixaServiceImpl.findByDates(sdfBD.parse(dto.getDataInicio() + " 00:00:00"), sdfBD.parse(dto.getDataFim() + " 23:59:59"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		BigDecimal valorUnitarioTotal = new BigDecimal(0);
		
		for (ItemBaixa e : list) {
			e.setDataFormatada(sdf.format(e.getBaixa().getData()));
			valorUnitarioTotal = e.getValorUnitarioTotal().add(valorUnitarioTotal);
		}
		
		mv.addObject("list", list);
		mv.addObject("valorUnitarioTotal", valorUnitarioTotal);
		mv.addObject("data", new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()));
		
		return mv;
	}
	
	@Override
	public ModelAndView estoqueGerar() {
		
		if (setupServiceImpl.sistemaExpirou()) 
			return new ModelAndView("login/expirado");
		
		ModelAndView mv = new ModelAndView("relatorio/estoquerel");
		List<Produto> list = produtoServiceImpl.findAllActive();
		
		BigDecimal custoUnitarioTotal = new BigDecimal(0);
		BigDecimal valorVendaUnitarioTotal = new BigDecimal(0);
		
		for (Produto p : list) {
			custoUnitarioTotal = p.getCustoUnitarioTotal().add(custoUnitarioTotal);
			valorVendaUnitarioTotal = p.getValorVendaUnitarioTotal().add(valorVendaUnitarioTotal);
		}
		
		mv.addObject("list", list);
		mv.addObject("custoUnitarioTotal", custoUnitarioTotal);
		mv.addObject("valorVendaUnitarioTotal", valorVendaUnitarioTotal);
		mv.addObject("data", new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()));
		return mv;
	}

}
