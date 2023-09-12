package com.douglas.fastTrackSupport.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.douglas.fastTrackSupport.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{
	
	Optional<Pessoa> findByCpf(String cpf);
	Optional<Pessoa> findByEmail(String email);
}
