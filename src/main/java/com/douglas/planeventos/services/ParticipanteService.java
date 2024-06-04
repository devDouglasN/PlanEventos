package com.douglas.planeventos.services;

import com.douglas.planeventos.domain.Participante;
import com.douglas.planeventos.domain.dtos.ParticipanteDTO;
import com.douglas.planeventos.enums.Perfil;
import com.douglas.planeventos.repositories.ParticipanteRepository;
import com.douglas.planeventos.services.exceptions.ObjectnotFoundException;
import com.douglas.planeventos.services.exceptions.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParticipanteService {

	private final ParticipanteRepository repository;
	private final BCryptPasswordEncoder encoder;

	public List<ParticipanteDTO> findAll() {
		log.info("Listando todos os participantes");
		List<Participante> participantes = repository.findAll();
		return participantes.stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	public ParticipanteDTO findById(Integer id) {
		log.info("Buscando participante com ID: " + id);
		Participante participante = repository.findById(id)
				.orElseThrow(() -> new ObjectnotFoundException("Participante não encontrado com o ID: " + id));
		return mapToDTO(participante);
	}

	public Participante findParticipanteById(Integer id) {
		log.info("Buscando participante com ID: " + id);
		return repository.findById(id)
				.orElseThrow(() -> new ObjectnotFoundException("Participante não encontrado com o ID: " + id));
	}


	public ParticipanteDTO findByCPF(String cpf) {
		log.info("Buscando participante com CPF: " + cpf);
		Participante participante = (Participante) repository.findByCpf(cpf)
				.orElseThrow(() -> new ObjectnotFoundException("Participante não encontrado com o CPF: " + cpf));
		return mapToDTO(participante);
	}

	public ParticipanteDTO create(ParticipanteDTO participanteDTO) {
		validaCpfEEmail(participanteDTO);
		log.info("Criando novo participante: " + participanteDTO);
		String encodedPassword = encoder.encode(participanteDTO.senha());
		Participante participante = new Participante(
				null,
				participanteDTO.nome(),
				participanteDTO.cpf(),
				participanteDTO.email(),
				encodedPassword,
				participanteDTO.perfis(),
				LocalDate.now()
		);
		return mapToDTO(repository.save(participante));
	}

	public ParticipanteDTO update(Integer id, ParticipanteDTO participanteDTO) {
		log.info("Atualizando participante de ID: " + id + ", com informações: " + participanteDTO);
		validaCpfEEmail(participanteDTO, id);

		Participante participante = repository.findById(id)
				.orElseThrow(() -> new ObjectnotFoundException("Participante não encontrado com o ID: " + id));

		participante.setNome(participanteDTO.nome());
		participante.setCpf(participanteDTO.cpf());
		participante.setEmail(participanteDTO.email());
		participante.setSenha(encoder.encode(participanteDTO.senha()));
		participante.setDataCriacao(participanteDTO.dataCriacao());

		return mapToDTO(repository.save(participante));
	}

	public void delete(Integer id) {
		log.info("Deletando participante com ID: " + id);
		repository.deleteById(id);
	}

	private void validaCpfEEmail(ParticipanteDTO participanteDTO) {
		Optional<Participante> objByCpf = repository.findByCpf(participanteDTO.cpf());
		if (objByCpf.isPresent()) {
			throw new DataIntegrityViolationException("CPF já está sendo utilizado");
		}

		Optional<Participante> objByEmail = repository.findByEmail(participanteDTO.email());
		if (objByEmail.isPresent()) {
			throw new DataIntegrityViolationException("E-mail já está sendo utilizado");
		}
	}

	private void validaCpfEEmail(ParticipanteDTO participanteDTO, Integer id) {
		Optional<Participante> objByCpf = repository.findByCpf(participanteDTO.cpf());
		if (objByCpf.isPresent() && (id == null || !objByCpf.get().getId().equals(id))) {
			throw new DataIntegrityViolationException("CPF já está sendo utilizado");
		}

		Optional<Participante> objByEmail = repository.findByEmail(participanteDTO.email());
		if (objByEmail.isPresent() && (id == null || !objByEmail.get().getId().equals(id))) {
			throw new DataIntegrityViolationException("E-mail já está sendo utilizado");
		}
	}
	private ParticipanteDTO mapToDTO(Participante participante) {
		return new ParticipanteDTO(
				participante.getId(),
				participante.getNome(),
				participante.getCpf(),
				participante.getEmail(),
				participante.getSenha(),
				participante.getPerfis().stream().map(Perfil::getCodigo).collect(Collectors.toSet()),
				participante.getDataCriacao()
		);
	}
}
