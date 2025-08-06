package com.challenge.app.vivo.backend.App.vivo.challenge.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Usuario")
@Entity(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String senha;
    private Role role;
    private boolean ativo;

    @OneToMany(mappedBy = "usuario")
    private List<Tarefa> tarefas;


    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.ativo = true;
    }


    public void deletarAtivo() {
        this.ativo = false;
    }
}
