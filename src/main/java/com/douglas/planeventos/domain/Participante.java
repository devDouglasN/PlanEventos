package com.douglas.planeventos.domain;

import com.douglas.planeventos.domain.dtos.ParticipanteDTO;
import com.douglas.planeventos.enums.Perfil;
import jakarta.persistence.*;

import java.io.Serial;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Participante extends Pessoa {

    @Serial
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;

    private Boolean active;

    public Participante() {
        super();
        addPerfil(Perfil.PARTICIPANTE);
    }

    public Participante(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        addPerfil(Perfil.PARTICIPANTE);
    }

    public Participante(Integer id, String nome, String cpf, String email, String senha, Set<Integer> perfis, LocalDate dataCriacao) {
        super(id, nome, cpf, email, senha);
        this.perfis = perfis != null ? perfis : Set.of(Perfil.PARTICIPANTE.getCodigo());
        this.dataCriacao = dataCriacao;
        this.active = true;
    }

    public Participante(ParticipanteDTO obj) {
        super();
        this.id = obj.id();
        this.nome = obj.nome();
        this.cpf = obj.cpf();
        this.email = obj.email();
        this.senha = obj.senha();
        this.perfis = obj.perfis() != null ? obj.perfis() : Set.of(Perfil.PARTICIPANTE.getCodigo());
        this.dataCriacao = obj.dataCriacao();
        this.active = true;
        addPerfil(Perfil.PARTICIPANTE);
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
