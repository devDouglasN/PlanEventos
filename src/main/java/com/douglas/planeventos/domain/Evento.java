package com.douglas.planeventos.domain;

import com.douglas.planeventos.enums.HorarioEvento;
import com.douglas.planeventos.enums.StatusEvento;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.BatchSize;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Evento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataEvento;

    private String local;

    private String descricao;

    private StatusEvento status;

    private HorarioEvento horario;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime horarioInicio;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime horarioFim;

    private Integer quantidadePessoas;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "evento_participantes",
            joinColumns = @JoinColumn(name = "evento_id"),
            inverseJoinColumns = @JoinColumn(name = "participante_id"))
    @BatchSize(size = 10)
    private Set<Participante> participantes = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "evento_organizadores",
            joinColumns = @JoinColumn(name = "evento_id"),
            inverseJoinColumns = @JoinColumn(name = "organizador_id"))
    @BatchSize(size = 10)
    private Set<Organizador> organizadores = new HashSet<>();

    public Evento() {
        super();
    }

    public Evento(Integer id, LocalDate dataEvento, String local, String descricao, StatusEvento status, HorarioEvento horario, LocalTime horarioInicio, LocalTime horarioFim, Integer quantidadePessoas, Set<Organizador> organizadores, Set<Participante> participantes) {
        this.id = id;
        this.dataEvento = dataEvento;
        this.local = local;
        this.descricao = descricao;
        this.status = status;
        this.horario = horario;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.quantidadePessoas = quantidadePessoas;
        this.organizadores = organizadores;
        this.participantes = participantes;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Evento other = (Evento) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
