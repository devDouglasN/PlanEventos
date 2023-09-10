package com.douglas.fastTrackSupport.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.douglas.fastTrackSupport.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{
}
