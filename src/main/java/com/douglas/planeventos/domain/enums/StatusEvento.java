package com.douglas.planeventos.domain.enums;

public enum StatusEvento  {

	ABERTO(0, "ABERTO"), 
	ANDAMENTO(1, "ANDAMENTO"), 
	ENCERRADO(2, "ENCERRADO");

	private Integer codigo;
	private String descricao;

	private StatusEvento(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static StatusEvento toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (StatusEvento x : StatusEvento.values()) {
			if (cod.equals(x.getCodigo())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Status inválido");
	}
}
