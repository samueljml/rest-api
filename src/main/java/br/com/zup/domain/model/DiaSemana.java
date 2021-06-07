package br.com.zup.domain.model;

public enum DiaSemana {
	SEGUNDA("segunda-Feira"), TERCA  ("terça-Feira"),
	QUARTA ("quarta-Feira"), QUINTA ("quinta-Feira"),
	SEXTA  ("sexta-Feira"), SABADO ("sabado-Feira"),
	DOMINGO("domingo-Feira");
	
	private String descricao;

	DiaSemana (String descricao) {
        this.descricao = descricao;
    }
    public String getDescricao() {
        return descricao;
    }
}
