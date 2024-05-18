package com.douglas.planeventos.domain;

import com.douglas.planeventos.domain.dtos.ParticipanteDTO;
import jakarta.persistence.*;

import java.io.Serial;
import java.util.stream.Collectors;

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
    }

    public Participante(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
    }

    public Participante(ParticipanteDTO obj) {
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
