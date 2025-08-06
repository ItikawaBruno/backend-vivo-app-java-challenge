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
    private String descricao;
    private String dataDeInicio;
    private String dataDeTermino;
    private Integer ponto;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

}
