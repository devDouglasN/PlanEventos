package com.douglas.planeventos.services;

import com.douglas.planeventos.domain.Participante;
import com.douglas.planeventos.domain.Pessoa;
import com.douglas.planeventos.domain.dtos.ParticipanteDTO;
import com.douglas.planeventos.repositories.ParticipanteRepository;
import com.douglas.planeventos.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipanteService {
	
	@Autowired
	private ParticipanteRepository repository;

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	public Participante findById(Integer id) {
		Optional<Participante> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " + id));
	}

	public List<Participante> findAll() {
		return repository.findAll();
	}

	public Participante create(ParticipanteDTO objDTO) {
		objDTO.setId(null);
		objDTO.setSenha(encoder.encode(objDTO.getSenha()));
		validaPorEmail(objDTO);
		Participante newObj = new Participante(objDTO);
		return repository.save(newObj);
	}
	
	public Participante update(Integer id, @Valid ParticipanteDTO objDTO) {
		objDTO.setId(id);
		Participante oldObj = findById(id);
		validaPorEmail(objDTO);
		oldObj = new Participante(objDTO);
		return repository.save(oldObj);
	}
	
	public void delete(Integer id) {
		Participante obj = findById(id);

		if (obj.getEventos().size() > 0) {
			throw new DataIntegrityViolationException("Participante possui eventos e não pode ser deletado!");
		}

		repository.deleteById(id);		
	}

	private void validaPorEmail(ParticipanteDTO objDTO) {
		Optional<Pessoa> obj = pessoaRepository.findByEmail(objDTO.getEmail());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
		}
	}
}
