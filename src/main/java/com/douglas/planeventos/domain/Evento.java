package com.douglas.planeventos.domain;

import com.douglas.planeventos.enums.HorarioEvento;
import com.douglas.planeventos.enums.StatusEvento;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Entity
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

    private LocalTime horarioInicio;

    private LocalTime horarioFim;
    private Integer quantidadePessoas;

    @JsonIgnore
    @OneToMany(mappedBy = "evento")
    private List<Organizador> organizadores = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "evento")
    private List<Participante> participantes = new ArrayList<>();

    public Evento() {
        super();
    }

    public Evento(Integer id, LocalDate dataEvento, String local, String descricao, StatusEvento status, HorarioEvento horario, LocalTime horarioInicio, LocalTime horarioFim, Integer quantidadePessoas, List<Organizador> organizadores, List<Participante> participantes) {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(LocalDate dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public StatusEvento getStatus() {
        return status;
    }

    public void setStatus(StatusEvento status) {
        this.status = status;
    }

    public HorarioEvento getHorario() {
        return horario;
    }

    public void setHorario(HorarioEvento horario) {
        this.horario = horario;
    }


    public LocalTime getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(LocalTime horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public LocalTime getHorarioFim() {
        return horarioFim;
    }

    public void setHorarioFim(LocalTime horarioFim) {
        this.horarioFim = horarioFim;
    }

    public List<Organizador> getOrganizadores() {
        return organizadores;
    }

    public void setOrganizadores(List<Organizador> organizadores) {
        this.organizadores = organizadores;
    }

    public List<Participante> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Participante> participantes) {
        this.participantes = participantes;
    }

    public Integer getQuantidadePessoas() {
        return quantidadePessoas;
    }

    public void setQuantidadePessoas(Integer quantidadePessoas) {
        this.quantidadePessoas = quantidadePessoas;
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
