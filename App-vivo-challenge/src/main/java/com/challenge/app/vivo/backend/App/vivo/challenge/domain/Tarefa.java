package com.challenge.app.vivo.backend.App.vivo.challenge.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "Tarefa")
@Entity(name = "tarefa")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    private String dataDeInicio;
    private String dataDeTermino;
    private Integer ponto;
    private boolean ativo;

    @ManyToOne
    @JoinColumn(name = "gestor_id")
    private Usuario gestor;

    @ManyToOne
    @JoinColumn(name = "colaborador_id")
    private Usuario colaborador;

    public Tarefa(String titulo, String descricao, String dataDeInicio, String dataDeTermino, Usuario colaborador, Usuario gestor) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataDeInicio = dataDeInicio;
        this.dataDeTermino = dataDeTermino;
        this.ponto = 0;
        this.ativo = true;
    }
}
