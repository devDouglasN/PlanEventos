package com.douglas.planeventos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.douglas.planeventos.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
