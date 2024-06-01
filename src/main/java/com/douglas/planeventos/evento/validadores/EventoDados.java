package com.douglas.planeventos.evento.validadores;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record EventoDados (

        @NotNull
        List<Integer> idsParticipantes,

        @NotNull
        List<Integer> idsOrganizadores,

        @NotNull
        LocalTime horarioInicio,

        @NotNull
        LocalTime horarioFim,

        @NotNull
        LocalDate dataEvento,

        String local,

        String descricao,

        Integer quantidadePessoas
) {
}