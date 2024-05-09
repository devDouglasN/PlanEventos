package com.douglas.planeventos.evento.validadores;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record EventoDados (

        @NotNull
        Integer idParticipante,

        @NotNull Integer idOrganizador,

        LocalDate dataEvento,

        @NotNull
        LocalTime horarioInicio,

        @NotNull
        LocalTime horarioFim,

        @NotNull
        String local,

        @NotNull
        String descricao
) {
}