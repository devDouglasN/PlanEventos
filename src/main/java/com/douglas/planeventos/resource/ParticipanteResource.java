package com.douglas.planeventos.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import com.douglas.planeventos.domain.Participante;
import com.douglas.planeventos.domain.dtos.ParticipanteDTO;
import com.douglas.planeventos.services.ParticipanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping(value = "/participantes")
public class ParticipanteResource {

	@Autowired
	private ParticipanteService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<ParticipanteDTO> findById(@PathVariable Integer id) {
		Participante obj = service.findById(id);
		return ResponseEntity.ok().body(new ParticipanteDTO(obj));
	}

	@GetMapping
	public ResponseEntity<List<ParticipanteDTO>> findAll() {
		List<Participante> list = service.findAll();
		List<ParticipanteDTO> listDTO = list.stream().map(obj -> new ParticipanteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@PostMapping
	public ResponseEntity<ParticipanteDTO> create(@Valid @RequestBody ParticipanteDTO objDTO) {
		Participante newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<ParticipanteDTO> update(@PathVariable Integer id, @Valid @RequestBody ParticipanteDTO objDTO) {
		Participante obj = service.update(id, objDTO);
		return ResponseEntity.ok().body(new ParticipanteDTO(obj));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ParticipanteDTO> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
