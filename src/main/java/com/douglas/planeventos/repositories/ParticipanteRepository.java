package com.douglas.planeventos.repositories;

import com.douglas.planeventos.domain.Participante;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ParticipanteRepository extends JpaRepository<Participante, Integer> {
}
