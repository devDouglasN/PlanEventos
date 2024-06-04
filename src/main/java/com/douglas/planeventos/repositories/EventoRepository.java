package com.douglas.planeventos.repositories;

import com.douglas.planeventos.domain.Evento;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface EventoRepository extends JpaRepository<Evento, Integer> {

    @EntityGraph(attributePaths = {"participantes", "organizadores"})
    Optional<Evento> findById(Integer id);

    @EntityGraph(attributePaths = {"participantes", "organizadores"})
    List<Evento> findAll();
}
