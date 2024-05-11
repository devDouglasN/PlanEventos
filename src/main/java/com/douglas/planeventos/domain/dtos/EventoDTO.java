package com.douglas.planeventos.domain.dtos;

import com.douglas.planeventos.domain.Evento;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.time.LocalDate;
import java.time.LocalTime;


public class EventoDTO {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataEvento;

    private String local;
    private String descricao;

    @NotNull(message = "O campo STATUS é requerido")
    private Integer status;

    @NotNull(message = "O campo HORARIO é requerido")
    private Integer horario;

    @NotNull(message = "O campo TECNICO é requerido")
    private Integer participante;

    @NotNull(message = "O campo CLIENTE é requerido")
    private Integer organizador;

    private LocalTime horarioInicio;
    private LocalTime horarioFim;

    private String nomeParticipante;
    private String nomeOrganizador;

    public EventoDTO() {
        super();
    }

    public EventoDTO(Evento obj) {
        this.id = obj.getId();
        this.dataEvento = obj.getDataEvento();
        this.local = obj.getLocal();
        this.descricao = obj.getDescricao();
        this.status = obj.getStatus().getCodigo();
        this.horario = obj.getHorario().getCodigo();
        this.organizador = obj.getOrganizador().getId();
        this.participante = obj.getParticipante().getId();
        this.nomeOrganizador = obj.getOrganizador().getNome();
        this.nomeParticipante = obj.getParticipante().getNome();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Integer getHorario() {
        return horario;
    }

    public void setHorario(Integer horario) {
        this.horario = horario;
    }

    public Integer getParticipante() {
        return participante;
    }

    public void setParticipante(Integer participante) {
        this.participante = participante;
    }

    public Integer getOrganizador() {
        return organizador;
    }

    public void setOrganizador(Integer organizador) {
        this.organizador = organizador;
    }

    public String getNomeParticipante() {
        return nomeParticipante;
    }

    public void setNomeParticipante(String nomeParticipante) {
        this.nomeParticipante = nomeParticipante;
    }

    public String getNomeOrganizador() {
        return nomeOrganizador;
    }

    public void setNomeOrganizador(String nomeOrganizador) {
        this.nomeOrganizador = nomeOrganizador;
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
}
