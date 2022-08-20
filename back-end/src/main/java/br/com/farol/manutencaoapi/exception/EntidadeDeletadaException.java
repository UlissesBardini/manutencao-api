package br.com.farol.manutencaoapi.exception;

public class EntidadeDeletadaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EntidadeDeletadaException() {
		super("A entidade não pode ter sido deletada");
	}

}
