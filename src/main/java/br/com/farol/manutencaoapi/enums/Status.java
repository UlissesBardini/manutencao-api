package br.com.farol.manutencaoapi.enums;

import lombok.Getter;

public enum Status {
	A("Ativo"),
	I("Inativo");
	
	@Getter
	private final String descricao;
	
	private Status(String descricao) {
		this.descricao = descricao;
	}
	
}
