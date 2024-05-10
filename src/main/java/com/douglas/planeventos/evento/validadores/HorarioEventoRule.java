package com.douglas.planeventos.evento.validadores;

import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class HorarioEventoRule implements ValidadorParaEvento {

    public void validador (EventoDados dados) {
        if (dados.horarioInicio().isBefore(LocalTime.of(19, 0)) ||
                dados.horarioFim().isAfter(LocalTime.of(3, 0))) {
            throw new ValidationException("O evento deve ocorrer entre 19h e 3h");
        }
    }
}
