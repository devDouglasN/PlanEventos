package com.douglas.planeventos.enums;

public enum Perfil {
	
	ADMIN(0, "ROLE_ADMIN"), 
	PARTICIPANTE(1, "ROLE_PARTICIPANTE"), 
	ORGANIZADOR(2, "ROLE_ORGANIZADOR");

	private Integer codigo;
	private String descricao;

	private Perfil(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Perfil toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (Perfil x : Perfil.values()) {
			if (cod.equals(x.getCodigo())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Perfil inválido");
	}
}