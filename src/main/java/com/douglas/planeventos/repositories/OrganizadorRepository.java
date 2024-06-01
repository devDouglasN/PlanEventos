package com.douglas.planeventos.repositories;

import com.douglas.planeventos.domain.Organizador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface OrganizadorRepository extends JpaRepository<Organizador, Integer>{
    @Query("""
			select o.active
			from Organizador o
			where   
			o.id = :id
			""")
    Boolean existsByIdAndActiveTrue(Integer id);


	Optional<Organizador> findByCpf(String cpf);

	Optional<Organizador> findByEmail(String email);
}
