package com.douglas.planeventos.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import com.douglas.planeventos.domain.Organizador;
import com.douglas.planeventos.domain.dtos.OrganizadorDTO;
import com.douglas.planeventos.services.OrganizadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping(value = "/organizadores")
public class OrganizadorResource {
	
	@Autowired
	private OrganizadorService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<OrganizadorDTO> findById(@PathVariable Integer id) {
		Organizador obj = service.findById(id);
		return ResponseEntity.ok().body(new OrganizadorDTO(obj));
	}
	
	@GetMapping
	public ResponseEntity<List<OrganizadorDTO>> findAll() {
		List<Organizador> list = service.findAll();
		List<OrganizadorDTO> listDTO = list.stream().map(obj -> new OrganizadorDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PostMapping
	public ResponseEntity<OrganizadorDTO> create(@Valid @RequestBody OrganizadorDTO objDTO) {
		Organizador newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<OrganizadorDTO> update(@PathVariable Integer id, @Valid @RequestBody OrganizadorDTO objDTO) {
		Organizador obj = service.update(id, objDTO);
		return ResponseEntity.ok().body(new OrganizadorDTO(obj));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<OrganizadorDTO> delete(@PathVariable Integer id) {
		service.delete(id); 
		return ResponseEntity.noContent().build();
	}
}
