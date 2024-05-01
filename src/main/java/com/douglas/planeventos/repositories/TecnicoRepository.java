package com.douglas.planeventos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.douglas.planeventos.domain.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer>{
}
