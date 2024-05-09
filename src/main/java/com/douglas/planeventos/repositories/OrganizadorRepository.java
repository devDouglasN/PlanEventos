package com.douglas.planeventos.repositories;

import com.douglas.planeventos.domain.Organizador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface OrganizadorRepository extends JpaRepository<Organizador, Integer>{
    @Query("""
			select o.active
			from Organizador o
			where   
			o.id = :id
			""")
    Boolean existsByIdAndActiveTrue(Integer id);
}
