package com.douglas.planeventos.evento.validadores;

import com.douglas.planeventos.services.exceptions.ValidationException;
import org.springframework.stereotype.Component;

@Component

public class QuantidadePessoasRule implements ValidadorParaEvento {

    private static final int MAX_PESSOAS = 200;

    public void validador (EventoDados dados) {
        if (dados.quantidadePessoas() > MAX_PESSOAS) {
            throw new ValidationException("A quantidade de pessoas excede o máximo permitido");
        }
    }
}
