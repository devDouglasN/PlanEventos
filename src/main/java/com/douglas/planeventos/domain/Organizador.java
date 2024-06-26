package com.douglas.planeventos.domain;

import com.douglas.planeventos.domain.dtos.OrganizadorDTO;
import com.douglas.planeventos.enums.Perfil;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
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
        addPerfil(Perfil.ORGANIZADOR);
    }

    public Organizador(Integer id, String nome, String cpf, String email, String senha, Set<Integer> perfis, LocalDate dataCriacao) {
        super(id, nome, cpf, email, senha);
        this.perfis = perfis != null ? perfis : Set.of(Perfil.ORGANIZADOR.getCodigo());
        this.dataCriacao = dataCriacao;
        this.active = true;
    }

    public Organizador(OrganizadorDTO obj) {
        super();
        this.id = obj.id();
        this.nome = obj.nome();
        this.cpf = obj.cpf();
        this.email = obj.email();
        this.senha = obj.senha();
        this.perfis = obj.perfis() != null ? obj.perfis() : Set.of(Perfil.ORGANIZADOR.getCodigo());
        this.dataCriacao = obj.dataCriacao();
        this.active = true;
        addPerfil(Perfil.ORGANIZADOR);
    }

}
