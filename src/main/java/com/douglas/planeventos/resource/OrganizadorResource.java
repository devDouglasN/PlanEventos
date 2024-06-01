package com.douglas.planeventos.resource;

import java.net.URI;
import java.util.List;

import com.douglas.planeventos.domain.dtos.OrganizadorDTO;
import com.douglas.planeventos.services.OrganizadorService;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping(value = "/organizadores")
public class OrganizadorResource {

	private final OrganizadorService service;

	public OrganizadorResource(OrganizadorService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<OrganizadorDTO>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<OrganizadorDTO> findById(@PathVariable Integer id) {
		return ResponseEntity.ok().body(service.findById(id));
	}

	@GetMapping("/cpf")
	public ResponseEntity<OrganizadorDTO> findByCPF(@RequestParam String cpf) {
		return ResponseEntity.ok().body(service.findByCPF(cpf));
	}

	@PostMapping
	public ResponseEntity<OrganizadorDTO> create(@Validated @RequestBody OrganizadorDTO organizadorDTO) {
		OrganizadorDTO newObj = service.create(organizadorDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.id()).toUri();
		return ResponseEntity.created(uri).body(newObj);
	}

	@PutMapping("/{id}")
	public ResponseEntity<OrganizadorDTO> update(@PathVariable Integer id, @Validated @RequestBody OrganizadorDTO organizadorDTO) {
		return ResponseEntity.ok().body(service.update(id, organizadorDTO));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
