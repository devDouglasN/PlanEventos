package com.douglas.planeventos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.douglas.planeventos.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {
}
