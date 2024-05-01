package com.douglas.planeventos.domain.enums;

public enum HorarioEvento {
	
	MANHA(0, "MANHA"), 
	TARDE(1, "TARDE"), 
	NOITE(2, "NOITE");

	private Integer codigo;
	private String descricao;

	private HorarioEvento(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static HorarioEvento toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (HorarioEvento x : HorarioEvento.values()) {
			if (cod.equals(x.getCodigo())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Horário inválido");
	}
}
