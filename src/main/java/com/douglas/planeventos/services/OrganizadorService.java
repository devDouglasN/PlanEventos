package com.douglas.planeventos.services;

import com.douglas.planeventos.domain.Organizador;
import com.douglas.planeventos.domain.Pessoa;
import com.douglas.planeventos.domain.dtos.OrganizadorDTO;
import com.douglas.planeventos.repositories.OrganizadorRepository;
import com.douglas.planeventos.repositories.PessoaRepository;
import com.douglas.planeventos.services.exceptions.ObjectnotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;


import jakarta.validation.Valid;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizadorService {
	
	@Autowired
	private OrganizadorRepository repository;

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	public Organizador findById(Integer id) {
		Optional<Organizador> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " + id));
	}

	public List<Organizador> findAll() {
		return repository.findAll();
	}

	public Organizador create(OrganizadorDTO objDTO) {
		objDTO.setId(null);
		objDTO.setSenha(encoder.encode(objDTO.getSenha()));
		validaPorEmail(objDTO);
		Organizador newObj = new Organizador(objDTO);
		return repository.save(newObj);
	}
	
	public Organizador update(Integer id, @Valid OrganizadorDTO objDTO) {
		objDTO.setId(id);
		Organizador oldObj = findById(id);
		validaPorEmail(objDTO);
		oldObj = new Organizador(objDTO);
		return repository.save(oldObj);
	}
	
	public void delete(Integer id) {
		Organizador obj = findById(id);

		repository.deleteById(id);		
	}

	private void validaPorEmail(OrganizadorDTO objDTO) {

		Optional<Pessoa> obj = pessoaRepository.findByEmail(objDTO.getEmail());

		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
		}
	}
}
