package com.douglas.planeventos.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.douglas.planeventos.domain.Evento;
import com.douglas.planeventos.domain.Organizador;
import com.douglas.planeventos.domain.Participante;
import com.douglas.planeventos.domain.dtos.EventoDTO;
import com.douglas.planeventos.evento.validadores.EventoDados;
import com.douglas.planeventos.evento.validadores.ValidadorParaEvento;
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
	private OrganizadorService organizadorService;

	@Autowired
	private ParticipanteService participanteService;

	@Autowired
	private List<ValidadorParaEvento> validadores;

	public Evento findById(Integer id) {
		Optional<Evento> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! ID: " + id));
	}

	public List<Evento> findAll() {
		return repository.findAll();
	}

	public Evento update(Integer id, @Valid EventoDTO objDTO) {
		objDTO.setId(id);
		Evento oldObj = findById(id);

		EventoDados eventoDados = new EventoDados(
				objDTO.getParticipante(),
				objDTO.getOrganizador(),
				objDTO.getDataEvento(),
				objDTO.getHorarioInicio(),
				objDTO.getHorarioFim(),
				null
		);

		oldObj = newEvento(eventoDados);
		return repository.save(oldObj);
	}


	public Evento newEvento(EventoDados eventoDados) {

		if(!participanteRepository.existsById(eventoDados.idParticipante())) {
			throw new ObjectnotFoundException("Participante não encontrado!");
		}

		if(!organizadorRepository.existsById(eventoDados.idOrganizador())) {
			throw new ObjectnotFoundException("Organizador não encontrado!");
		}

		Participante participante = participanteService.findById(eventoDados.idParticipante());
		Organizador organizador = organizadorService.findById(eventoDados.idOrganizador());

		validadores.forEach(validator -> validator.validador(eventoDados));

		Evento evento = new Evento();
		evento.setDataEvento(eventoDados.dataEvento());
		evento.setHorarioInicio(eventoDados.horarioInicio());
		evento.setHorarioFim(eventoDados.horarioFim());

		List<Participante> participantes = new ArrayList<>();
		participantes.add(participante);
		evento.setParticipantes(participantes);

		List<Organizador> organizadores = new ArrayList<>();
		organizadores.add(organizador);
		evento.setOrganizadores(organizadores);

		return repository.save(evento);
	}
}
