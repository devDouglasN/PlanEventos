package com.douglas.planeventos.evento.validadores;

public class QuantidadePessoasRule {

    private static final int MAX_PESSOAS = 200;

    public void validarQuantidadePessoas(EventoDados dados) throws Exception {
        if (dados.quantidadePessoas() > MAX_PESSOAS) {
            throw new Exception("A quantidade de pessoas excede o máximo permitido");
        }
    }
}
