package br.com.zup.domain.model;

import java.time.DayOfWeek;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Veiculo {
	
	private String marca;
	private String modelo;
	private Long ano;
	@JsonProperty("Valor")
	private String valor;
	private DiaSemana diaDoRodizio;
	private Boolean rodizioAtivo;

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Long getAno() {
		return ano;
	}

	public void setAno(Long ano) {
		this.ano = ano;
	}
	
	public void setDiaDoRodizio(DiaSemana diaDoRodizio) {
		this.diaDoRodizio = diaDoRodizio;
	}

	public Boolean getRodizioAtivo() {
		return rodizioAtivo;
	}

	public void setRodizioAtivo(Boolean rodizioAtivo) {
		this.rodizioAtivo = rodizioAtivo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	
	public DiaSemana getDiaDoRodizio() {
		String opcao = String.valueOf(ano).substring(3);

		switch (opcao) {
			case ("0"):
			case ("1"):
				return DiaSemana.SEGUNDA;
			case ("2"):
			case ("3"):
				return DiaSemana.TERCA;
			case ("4"):
			case ("5"):
				return DiaSemana.QUARTA;
			case ("6"):
			case ("7"):
				return DiaSemana.QUINTA;
			case ("8"):
			case ("9"):
				return DiaSemana.SEXTA;
		}
		return DiaSemana.SEGUNDA;
	}

	private DayOfWeek getDataSistema() {

		DayOfWeek data = LocalDate.now().getDayOfWeek();

		return data;
	}
	
	public void updateRodizio() {
		DayOfWeek diaSemanaSistema = getDataSistema();
		this.diaDoRodizio = getDiaDoRodizio();

		if (diaSemanaSistema.ordinal() == this.diaDoRodizio.ordinal()) {
			this.rodizioAtivo = true;
		} else {
			this.rodizioAtivo = false;
		}
	}
}
