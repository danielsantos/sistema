package com.aplinotech.cadastrocliente.model.dto;

import org.springframework.stereotype.Controller;

@Controller
public class RelatorioDTO {

	private String dataInicio;
	
	private String dataFim;

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getDataFim() {
		return dataFim;
	}

	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}
	
}
