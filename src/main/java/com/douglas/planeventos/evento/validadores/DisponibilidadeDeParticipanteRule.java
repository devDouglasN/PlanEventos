package com.douglas.planeventos.evento.validadores;

import com.douglas.planeventos.repositories.ParticipanteRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;

public class DisponibilidadeDeParticipanteRule {

    @Autowired
    private ParticipanteRepository repository;

    public void validador (EventoDados dados) {
        if(dados.idParticipante() == null) {
            return;
        }

        var participanteExiste = repository.existsByIdAndActiveTrue(dados.idParticipante());

        if(!participanteExiste) {}
        throw new ValidationException("Participante não encontrado!");
    }
}
