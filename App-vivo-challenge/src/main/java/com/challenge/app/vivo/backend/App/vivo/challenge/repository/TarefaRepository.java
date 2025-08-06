package com.challenge.app.vivo.backend.App.vivo.challenge.repository;

import com.challenge.app.vivo.backend.App.vivo.challenge.domain.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa,Long> {
}
