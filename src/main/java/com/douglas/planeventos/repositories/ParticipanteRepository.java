package com.douglas.planeventos.repositories;

import com.douglas.planeventos.domain.Participante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface ParticipanteRepository extends JpaRepository<Participante, Integer> {

    @Query("""
			select p.active
			from Participante p
			where   
			p.id = :id
			""")
    Boolean existsByIdAndActiveTrue(Integer id);

    Optional<Participante> findByCpf(String cpf);

	Optional<Participante> findByEmail(String email);
}
