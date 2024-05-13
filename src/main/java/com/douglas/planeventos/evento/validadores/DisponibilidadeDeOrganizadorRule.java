package com.douglas.planeventos.evento.validadores;

import com.douglas.planeventos.repositories.OrganizadorRepository;
import com.douglas.planeventos.services.exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DisponibilidadeDeOrganizadorRule implements ValidadorParaEvento {

    @Autowired
    private OrganizadorRepository repository;

    public void validador (EventoDados dados) {

        if(dados.idOrganizador() == null) {
            return;
        }

        var organizadorExiste = repository.existsByIdAndActiveTrue(dados.idOrganizador());

        if(!organizadorExiste) {
            throw new ValidationException("Organizador não encontrado!");
        }

    }
}
