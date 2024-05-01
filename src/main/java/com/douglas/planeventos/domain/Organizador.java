package com.douglas.planeventos.domain;

import com.douglas.planeventos.domain.dtos.OrganizadorDTO;
import com.douglas.planeventos.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Organizador extends Pessoa {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @OneToMany(mappedBy = "organizador")
    private List<Evento> eventos = new ArrayList<>();

    public Organizador() {
        super();
        addPerfil(Perfil.ORGANIZADOR);
    }

    public Organizador(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
    }

    public Organizador(OrganizadorDTO obj) {
        super();
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
        this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao = obj.getDataCriacao();
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }
}
