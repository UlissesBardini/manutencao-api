package br.com.farol.manutencaoapi.enums;

import lombok.Getter;

public enum Disponibilidade {
	D("Disponível"),
	I("Indisponível");
	
	@Getter
	private final String descricao;
	
	private Disponibilidade(String descricao) {
		this.descricao = descricao;
	}
}
