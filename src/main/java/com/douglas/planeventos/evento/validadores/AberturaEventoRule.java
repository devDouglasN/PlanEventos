package com.douglas.planeventos.evento.validadores;

import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class AberturaEventoRule implements ValidadorParaEvento {

    public void validador (EventoDados dados) {
        if (!(dados.data().equals(DayOfWeek.FRIDAY) || dados.data().equals(DayOfWeek.SATURDAY) ||
                dados.data().equals(DayOfWeek.SUNDAY))) {
            throw new ValidationException("O evento deve ocorrer apenas às sextas, sábados ou domingos!");
        }
    }
}
