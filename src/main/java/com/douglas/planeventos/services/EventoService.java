package com.douglas.planeventos.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.douglas.planeventos.domain.Evento;
import com.douglas.planeventos.domain.Organizador;
import com.douglas.planeventos.domain.Participante;
import com.douglas.planeventos.domain.dtos.EventoDTO;
import com.douglas.planeventos.enums.HorarioEvento;
import com.douglas.planeventos.enums.StatusEvento;
import com.douglas.planeventos.repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.Valid;

public class EventoService {
	
	@Autowired
	private EventoRepository repository;

	@Autowired
	private OrganizadorService organizadorService;

	@Autowired
	private ParticipanteService participanteService;

	public Evento findById(Integer id) {
		Optional<Evento> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! ID: " + id));
	}

	public List<Evento> findAll() {
		return repository.findAll();
	}

	public Evento create(@Valid EventoDTO obj) {
		return repository.save(newEvento(obj));
	}

	public Evento update(Integer id, @Valid EventoDTO objDTO) {
		objDTO.setId(id);
		Evento oldObj = findById(id);
		oldObj = newEvento(objDTO);
		return repository.save(oldObj);
	}

	private Evento newEvento(EventoDTO obj) {
		Organizador organizador = organizadorService.findById(obj.getOrganizador());
		Participante participante = participanteService.findById(obj.getParticipante());

		Evento evento = new Evento();
		if (obj.getId() != null) {
			evento.setId(obj.getId());
		}
		
		if(obj.getStatus().equals(2)) {
			evento.setDataEvento(LocalDate.now());
		}

		evento.setOrganizador(organizador);
		evento.setParticipante(participante);
		evento.setStatus(StatusEvento.toEnum(obj.getStatus()));
		evento.setHorario(HorarioEvento.toEnum(obj.getHorario()));
		evento.setDescricao(obj.getDescricao());
		evento.setLocal(obj.getLocal());
		return evento;
	}
}
