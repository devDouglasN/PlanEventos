package com.douglas.planeventos.domain.dtos;

import com.douglas.planeventos.domain.Participante;
import com.douglas.planeventos.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

public record ParticipanteDTO(
        Integer id,
        @NotNull(message = "O campo NOME é requerido") String nome,
        @NotNull(message = "O campo CPF é requerido") @CPF String cpf,
        @NotNull(message = "O campo EMAIL é requerido") String email,
        @NotNull(message = "O campo SENHA é requerido") String senha,
        Set<Integer> perfis,
        @JsonFormat(pattern = "dd/MM/yyyy") LocalDate dataCriacao
) {
    public ParticipanteDTO() {
        this(null, null, null, null, null, Set.of(Perfil.PARTICIPANTE.getCodigo()), LocalDate.now());
    }

    public ParticipanteDTO(Participante obj) {
        this(
                obj.getId(),
                obj.getNome(),
                obj.getCpf(),
                obj.getEmail(),
                obj.getSenha(),
                obj.getPerfis().stream().map(Perfil::getCodigo).collect(Collectors.toSet()),
                obj.getDataCriacao()
        );
    }
}
