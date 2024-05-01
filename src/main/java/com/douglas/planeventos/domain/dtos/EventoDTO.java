package com.douglas.planeventos.domain.dtos;

import com.douglas.planeventos.domain.Evento;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.time.LocalDate;

@Getter
@Setter
public class EventoDTO {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataEvento;

    private String local;
    private String descricao;

    @NotNull(message = "O campo STATUS é requerido")
    private Integer status;

    @NotNull(message = "O campo HORARIO é requerido")
    private Integer horario;

    @NotNull(message = "O campo TECNICO é requerido")
    private Integer participante;

    @NotNull(message = "O campo CLIENTE é requerido")
    private Integer organizador;

    private String nomeParticipante;
    private String nomeOrganizador;

    public EventoDTO() {
        super();
    }

    public EventoDTO(Evento obj) {
        this.id = obj.getId();
        this.dataEvento = obj.getDataEvento();
        this.local = obj.getLocal();
        this.descricao = obj.getDescricao();
        this.status = obj.getStatus().getCodigo();
        this.horario = obj.getHorario().getCodigo();
        this.organizador = obj.getOrganizador().getId();
        this.participante = obj.getParticipante().getId();
        this.nomeOrganizador = obj.getOrganizador().getNome();
        this.nomeParticipante = obj.getParticipante().getNome();
    }
}
