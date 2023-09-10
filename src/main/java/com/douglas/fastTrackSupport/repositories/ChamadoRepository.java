package com.douglas.fastTrackSupport.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.douglas.fastTrackSupport.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {
}
