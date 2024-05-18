package com.douglas.planeventos.domain;

import com.douglas.planeventos.domain.dtos.OrganizadorDTO;
import com.douglas.planeventos.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Organizador extends Pessoa {

    @Serial
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;

    private Boolean active;

    public Organizador() {
        super();
        addPerfil(Perfil.ORGANIZADOR);
    }

    public Organizador(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
    }

    public Organizador(OrganizadorDTO obj) {
        super();
        this.active = true;
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
        this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao = obj.getDataCriacao();
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
