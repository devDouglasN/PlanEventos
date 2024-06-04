package com.douglas.planeventos.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.douglas.planeventos.domain.Evento;
import com.douglas.planeventos.domain.Organizador;
import com.douglas.planeventos.domain.Participante;
import com.douglas.planeventos.domain.dtos.EventoDTO;
import com.douglas.planeventos.enums.StatusEvento;
import com.douglas.planeventos.evento.validadores.EventoDados;
import com.douglas.planeventos.evento.validadores.EventoValidacaoDados;
import com.douglas.planeventos.evento.validadores.ValidadorParaEvento;
import com.douglas.planeventos.evento.validadores.ValidadorParaEventoValidacao;
import com.douglas.planeventos.repositories.EventoRepository;
import com.douglas.planeventos.repositories.OrganizadorRepository;
import com.douglas.planeventos.repositories.ParticipanteRepository;
import com.douglas.planeventos.services.exceptions.ObjectnotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class EventoService {

    @Autowired
    private EventoRepository repository;

    @Autowired
    private ParticipanteRepository participanteRepository;

    @Autowired
    private OrganizadorRepository organizadorRepository;

    @Autowired
    private List<ValidadorParaEvento> validadoresParaEvento;

    @Autowired
    private List<ValidadorParaEventoValidacao> validadoresParaEventoValidacao;

    public Evento findById(Integer id) {
        Optional<Evento> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! ID: " + id));
    }

    public List<Evento> findAll() {
        return repository.findAll();
    }

    public Evento update(Integer id, @Valid EventoDTO objDTO) {
        Evento evento = findById(id);
        evento.setDataEvento(objDTO.dataEvento());
        evento.setLocal(objDTO.local());
        evento.setDescricao(objDTO.descricao());
        evento.setStatus(objDTO.status());
        evento.setHorarioInicio(objDTO.horarioInicio());
        evento.setHorarioFim(objDTO.horarioFim());
        evento.setQuantidadePessoas(objDTO.quantidadePessoas());

        Set<Participante> participantes = objDTO.participantes().stream()
                .map(participanteId -> participanteRepository.findById(participanteId)
                        .orElseThrow(() -> new ObjectnotFoundException("Participante não encontrado com o ID: " + participanteId)))
                .collect(Collectors.toSet());
        evento.setParticipantes(participantes);

        Set<Organizador> organizadores = objDTO.organizadores().stream()
                .map(organizadorId -> organizadorRepository.findById(organizadorId)
                        .orElseThrow(() -> new ObjectnotFoundException("Organizador não encontrado com o ID: " + organizadorId)))
                .collect(Collectors.toSet());
        evento.setOrganizadores(organizadores);

        for (Integer idParticipante : objDTO.participantes()) {
            EventoValidacaoDados validacaoDados = new EventoValidacaoDados(
                    idParticipante,
                    null,
                    objDTO.horarioInicio(),
                    objDTO.horarioFim(),
                    objDTO.dataEvento()
            );
            validadoresParaEventoValidacao.forEach(validator -> validator.validador(validacaoDados));
        }

        for (Integer idOrganizador : objDTO.organizadores()) {
            EventoValidacaoDados validacaoDados = new EventoValidacaoDados(
                    null,
                    idOrganizador,
                    objDTO.horarioInicio(),
                    objDTO.horarioFim(),
                    objDTO.dataEvento()
            );
            validadoresParaEventoValidacao.forEach(validator -> validator.validador(validacaoDados));
        }

        return repository.save(evento);
    }

    public EventoDTO newEvento(EventoDados eventoDados) {
        validadoresParaEvento.forEach(validator -> validator.validador(eventoDados));

        Set<Participante> participantes = eventoDados.idsParticipantes().stream()
                .map(participanteId -> {
                    EventoValidacaoDados validacaoDados = new EventoValidacaoDados(
                            participanteId,
                            null,
                            eventoDados.horarioInicio(),
                            eventoDados.horarioFim(),
                            eventoDados.dataEvento()
                    );
                    validadoresParaEventoValidacao.forEach(validator -> validator.validador(validacaoDados));
                    return participanteRepository.findById(participanteId)
                            .orElseThrow(() -> new ObjectnotFoundException("Participante não encontrado com o ID: " + participanteId));
                })
                .collect(Collectors.toSet());

        Set<Organizador> organizadores = eventoDados.idsOrganizadores().stream()
                .map(organizadorId -> {
                    EventoValidacaoDados validacaoDados = new EventoValidacaoDados(
                            null,
                            organizadorId,
                            eventoDados.horarioInicio(),
                            eventoDados.horarioFim(),
                            eventoDados.dataEvento()
                    );
                    validadoresParaEventoValidacao.forEach(validator -> validator.validador(validacaoDados));
                    return organizadorRepository.findById(organizadorId)
                            .orElseThrow(() -> new ObjectnotFoundException("Organizador não encontrado com o ID: " + organizadorId));
                })
                .collect(Collectors.toSet());

        Evento evento = new Evento();
        evento.setDataEvento(eventoDados.dataEvento());
        evento.setLocal(eventoDados.local());
        evento.setDescricao(eventoDados.descricao());
        evento.setStatus(StatusEvento.ABERTO);
        evento.setHorarioInicio(eventoDados.horarioInicio());
        evento.setHorarioFim(eventoDados.horarioFim());
        evento.setQuantidadePessoas(eventoDados.quantidadePessoas());
        evento.setParticipantes(participantes);
        evento.setOrganizadores(organizadores);

        Evento savedEvento = repository.save(evento);
        return updateEventoDTOWithNames(savedEvento);
    }

    private EventoDTO updateEventoDTOWithNames(Evento evento) {
        List<String> participanteNomes = evento.getParticipantes().stream()
                .map(Participante::getNome)
                .collect(Collectors.toList());

        List<String> organizadorNomes = evento.getOrganizadores().stream()
                .map(Organizador::getNome)
                .collect(Collectors.toList());

        return new EventoDTO(
                evento.getId(),
                evento.getDataEvento(),
                evento.getLocal(),
                evento.getDescricao(),
                evento.getStatus(),
                evento.getHorarioInicio(),
                evento.getHorarioFim(),
                evento.getQuantidadePessoas(),
                evento.getParticipantes().stream().map(Participante::getId).collect(Collectors.toSet()),
                evento.getOrganizadores().stream().map(Organizador::getId).collect(Collectors.toSet()),
                String.join(", ", participanteNomes),
                String.join(", ", organizadorNomes)
        );
    }
}
