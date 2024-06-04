package com.douglas.planeventos.services;

import com.douglas.planeventos.domain.Organizador;
import com.douglas.planeventos.domain.dtos.OrganizadorDTO;
import com.douglas.planeventos.enums.Perfil;
import com.douglas.planeventos.repositories.OrganizadorRepository;
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
public class OrganizadorService {

	private final OrganizadorRepository repository;
	private final BCryptPasswordEncoder encoder;

	public List<OrganizadorDTO> findAll() {
		log.info("Listando todos os organizadores");
		List<Organizador> organizadores = repository.findAll();
		return organizadores.stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	public OrganizadorDTO findById(Integer id) {
		log.info("Buscando organizador com ID: " + id);
		Organizador organizador = repository.findById(id)
				.orElseThrow(() -> new ObjectnotFoundException("Organizador não encontrado com o ID: " + id));
		return mapToDTO(organizador);
	}

	public Organizador findOrganizadorById(Integer id) {
		log.info("Buscando organizador com ID: " + id);
		return repository.findById(id)
				.orElseThrow(() -> new ObjectnotFoundException("Organizador não encontrado com o ID: " + id));
	}

	public OrganizadorDTO findByCPF(String cpf) {
		log.info("Buscando organizador com CPF: " + cpf);
		Organizador organizador = repository.findByCpf(cpf)
				.orElseThrow(() -> new ObjectnotFoundException("Organizador não encontrado com o CPF: " + cpf));
		return mapToDTO(organizador);
	}

	public OrganizadorDTO create(OrganizadorDTO organizadorDTO) {
		validaCpfEEmail(organizadorDTO, null);
		log.info("Criando novo organizador: " + organizadorDTO);
		String encodedPassword = encoder.encode(organizadorDTO.senha());
		Organizador organizador = new Organizador(
				null,
				organizadorDTO.nome(),
				organizadorDTO.cpf(),
				organizadorDTO.email(),
				encodedPassword,
				organizadorDTO.perfis(),
				LocalDate.now()
		);
		return mapToDTO(repository.save(organizador));
	}

	public OrganizadorDTO update(Integer id, OrganizadorDTO organizadorDTO) {
		log.info("Atualizando organizador de ID: " + id + ", com informações: " + organizadorDTO);
		validaCpfEEmail(organizadorDTO, id);

		Organizador organizador = repository.findById(id)
				.orElseThrow(() -> new ObjectnotFoundException("Organizador não encontrado com o ID: " + id));

		organizador.setNome(organizadorDTO.nome());
		organizador.setCpf(organizadorDTO.cpf());
		organizador.setEmail(organizadorDTO.email());
		organizador.setSenha(encoder.encode(organizadorDTO.senha()));
		organizador.setDataCriacao(organizadorDTO.dataCriacao());

		return mapToDTO(repository.save(organizador));
	}

	public void delete(Integer id) {
		log.info("Deletando organizador com ID: " + id);
		repository.deleteById(id);
	}

	private void validaCpfEEmail(OrganizadorDTO organizadorDTO) {
		Optional<Organizador> objByCpf = repository.findByCpf(organizadorDTO.cpf());
		if (objByCpf.isPresent()) {
			throw new DataIntegrityViolationException("CPF já está sendo utilizado");
		}

		Optional<Organizador> objByEmail = repository.findByEmail(organizadorDTO.email());
		if (objByEmail.isPresent()) {
			throw new DataIntegrityViolationException("E-mail já está sendo utilizado");
		}
	}

	private void validaCpfEEmail(OrganizadorDTO organizadorDTO, Integer id) {
		Optional<Organizador> objByCpf = repository.findByCpf(organizadorDTO.cpf());
		if (objByCpf.isPresent() && (id == null || !objByCpf.get().getId().equals(id))) {
			throw new DataIntegrityViolationException("CPF já está sendo utilizado");
		}

		Optional<Organizador> objByEmail = repository.findByEmail(organizadorDTO.email());
		if (objByEmail.isPresent() && (id == null || !objByEmail.get().getId().equals(id))) {
			throw new DataIntegrityViolationException("E-mail já está sendo utilizado");
		}
	}

	private OrganizadorDTO mapToDTO(Organizador organizador) {
		return new OrganizadorDTO(
				organizador.getId(),
				organizador.getNome(),
				organizador.getCpf(),
				organizador.getEmail(),
				organizador.getSenha(),
				organizador.getPerfis().stream().map(Perfil::getCodigo).collect(Collectors.toSet()),
				organizador.getDataCriacao()
		);
	}

}
