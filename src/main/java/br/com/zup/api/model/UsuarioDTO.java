package br.com.zup.api.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.zup.domain.model.Veiculo;

public class UsuarioDTO {
	
	private String nome;
	private String email;
	private String cpf;
	private LocalDate nascimento;
	private List<Veiculo> veiculos = new ArrayList<>();
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public LocalDate getNascimento() {
		return nascimento;
	}
	public void setNascimento(LocalDate nascimento) {
		this.nascimento = nascimento;
	}
	public List<Veiculo> getVeiculos() {
		return veiculos;
	}
	public void setVeiculos(List<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}
}
