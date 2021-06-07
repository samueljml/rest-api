package br.com.zup.api.model;

import br.com.zup.domain.model.DiaSemana;

public class VeiculoModel {

	private String marca;
	private String modelo;
	private Long ano;
	private DiaSemana rodizio;
	private boolean estaNoRodizio;
	private double valor;
	
	public boolean isEstaNoRodizio() {
		return estaNoRodizio;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
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
	public DiaSemana getRodizio() {
		return rodizio;
	}
	public void setRodizio(DiaSemana rodizio) {
		this.rodizio = rodizio;
	}
	public boolean isRodizioAtivo() {
		return estaNoRodizio;
	}
	public void setEstaNoRodizio(boolean estaNoRodizio) {
		this.estaNoRodizio = estaNoRodizio;
	}
}
