package com.douglas.planeventos.resource;

import java.net.URI;
import java.util.List;

import com.douglas.planeventos.domain.dtos.ParticipanteDTO;
import com.douglas.planeventos.services.ParticipanteService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping("participantes")
public class ParticipanteResource {


	private final ParticipanteService service;

	public ParticipanteResource(ParticipanteService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<ParticipanteDTO>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ParticipanteDTO> findById(@PathVariable Integer id) {
		return ResponseEntity.ok().body(service.findById(id));
	}

	@GetMapping("/cpf")
	public ResponseEntity<ParticipanteDTO> findByCPF(@RequestParam String cpf) {
		return ResponseEntity.ok().body(service.findByCPF(cpf));
	}

	@PostMapping
	public ResponseEntity<ParticipanteDTO> create(@Validated @RequestBody ParticipanteDTO participanteDTO) {
		ParticipanteDTO newObj = service.create(participanteDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.id()).toUri();
		return ResponseEntity.created(uri).body(newObj);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ParticipanteDTO> update(@PathVariable Integer id, @Validated @RequestBody ParticipanteDTO participanteDTO) {
		return ResponseEntity.ok().body(service.update(id, participanteDTO));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
