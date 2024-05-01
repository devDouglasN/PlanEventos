package com.douglas.planeventos.repositories;

import com.douglas.planeventos.domain.Evento;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EventoRepository extends JpaRepository<Evento, Integer> {
}
