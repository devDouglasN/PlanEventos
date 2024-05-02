package com.douglas.planeventos.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import com.douglas.planeventos.domain.Evento;
import com.douglas.planeventos.domain.dtos.EventoDTO;
import com.douglas.planeventos.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/eventos")
public class EventoResource {

	@Autowired
	private EventoService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<EventoDTO> findById(@PathVariable Integer id) {
		Evento obj = service.findById(id);
		return ResponseEntity.ok().body(new EventoDTO(obj));
	}
	
	@GetMapping
	public ResponseEntity<List<EventoDTO>> findAll() {
		List<Evento> list = service.findAll();
		List<EventoDTO> listDTO = list.stream().map(obj -> new EventoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PostMapping
	public ResponseEntity<EventoDTO> create(@Valid @RequestBody EventoDTO obj) {
		Evento newObj = service.create(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<EventoDTO> update(@PathVariable Integer id, @Valid @RequestBody EventoDTO objDTO) {
		Evento newObj = service.update(id, objDTO);
		return ResponseEntity.ok().body(new EventoDTO(newObj));
	}
}
