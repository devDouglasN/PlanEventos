package com.douglas.planeventos.evento.validadores;

import com.douglas.planeventos.services.exceptions.ValidationException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class AberturaEventoRule implements ValidadorParaEvento {

    public void validador (EventoDados dados) {

        var data = dados.dataEvento();

        var sexta = data.getDayOfWeek().equals(DayOfWeek.FRIDAY);
        var sabado = data.getDayOfWeek().equals(DayOfWeek.SATURDAY);
        var domingo = data.getDayOfWeek().equals(DayOfWeek.SUNDAY);

        if (!(sexta || sabado || domingo)) {
            throw new ValidationException("O evento deve ocorrer apenas às sextas, sábados ou domingos!");
        }
    }
}
