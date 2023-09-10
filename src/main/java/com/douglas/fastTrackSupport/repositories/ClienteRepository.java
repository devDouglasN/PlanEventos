package com.douglas.fastTrackSupport.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.douglas.fastTrackSupport.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
