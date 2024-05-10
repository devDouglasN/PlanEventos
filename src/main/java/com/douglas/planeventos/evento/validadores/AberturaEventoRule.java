package com.douglas.planeventos.evento.validadores;

import java.time.DayOfWeek;

public class AberturaEventoRule {

    public void validarAberturaEvento(EventoDados dados) throws Exception {
        if (!(dados.data().equals(DayOfWeek.FRIDAY) || dados.data().equals(DayOfWeek.SATURDAY) ||
                dados.data().equals(DayOfWeek.SUNDAY))) {
            throw new Exception("O evento deve ocorrer apenas às sextas, sábados ou domingos!");
        }
    }
}
