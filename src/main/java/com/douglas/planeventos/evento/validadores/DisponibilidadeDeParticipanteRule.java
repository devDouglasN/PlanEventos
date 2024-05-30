package com.douglas.planeventos.evento.validadores;

import com.douglas.planeventos.repositories.ParticipanteRepository;
import com.douglas.planeventos.services.exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DisponibilidadeDeParticipanteRule implements ValidadorParaEvento {

    @Autowired
    private ParticipanteRepository repository;

    public void validador (EventoDados dados) {
        if(dados.idParticipante() == null) {
            return;
        }

        var participanteExiste = repository.existsByIdAndActiveTrue(dados.idParticipante());

        if(!participanteExiste) {
            throw new ValidationException("Participante não encontrado!");
        }
    }
}
