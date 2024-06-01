package com.douglas.planeventos.evento.validadores;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record EventoValidacaoDados(

        Integer idParticipante,
        Integer idOrganizador,

        @NotNull
        LocalTime horarioInicio,

        @NotNull
        LocalTime horarioFim,

        @NotNull
        LocalDate dataEvento
) {
}