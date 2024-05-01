package com.douglas.planeventos.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class Evento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataEvento;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private String local;

    private String descricao;
    private StatusEvento status;
    private HorarioEvento horario;

    @ManyToOne
    @JoinColumn(name = "organizador_id")
    private Organizador organizador;


    @ManyToOne
    @JoinColumn(name = "participante_id")
    private Participante participante;


    public Evento() {
        super();
    }

    public Evento(Integer id, LocalDate dataEvento, String local, String descricao, StatusEvento status, HorarioEvento horario, Organizador organizador, Participante participante) {
        super();
        this.id = id;
        this.dataEvento = dataEvento;
        this.local = local;
        this.descricao = descricao;
        this.status = status;
        this.horario = horario;
        this.organizador = organizador;
        this.participante = participante;
    }
}
