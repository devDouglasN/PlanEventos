package com.douglas.planeventos.domain.dtos;

import com.douglas.planeventos.domain.Evento;
import com.douglas.planeventos.domain.Organizador;
import com.douglas.planeventos.domain.Participante;
import com.douglas.planeventos.enums.StatusEvento;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public record EventoDTO(

        Integer id,

        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataEvento,

        String local,
        String descricao,

        @NotNull(message = "O campo STATUS é requerido")
        StatusEvento status,

        @JsonFormat(pattern = "HH:mm:ss")
        LocalTime horarioInicio,

        @JsonFormat(pattern = "HH:mm:ss")
        LocalTime horarioFim,

        Integer quantidadePessoas,

        @NotNull(message = "O campo Participante é requerido")
        List<Integer> participantes,

        @NotNull(message = "O campo Organizador é requerido")
        List<Integer> organizadores,

        String nomeParticipante,
        String nomeOrganizador
) {
    public EventoDTO(Evento obj) {
        this(
                obj.getId(),
                obj.getDataEvento(),
                obj.getLocal(),
                obj.getDescricao(),
                obj.getStatus(),
                obj.getHorarioInicio(),
                obj.getHorarioFim(),
                obj.getQuantidadePessoas(),
                obj.getParticipantes().stream().map(Participante::getId).collect(Collectors.toList()),
                obj.getOrganizadores().stream().map(Organizador::getId).collect(Collectors.toList()),
                obj.getParticipantes().stream().map(Participante::getNome).collect(Collectors.joining(", ")),
                obj.getOrganizadores().stream().map(Organizador::getNome).collect(Collectors.joining(", "))
        );
    }
}

