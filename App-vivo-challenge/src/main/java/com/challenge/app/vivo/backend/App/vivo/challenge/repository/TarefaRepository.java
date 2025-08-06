package com.challenge.app.vivo.backend.App.vivo.challenge.repository;

import com.challenge.app.vivo.backend.App.vivo.challenge.domain.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TarefaRepository extends JpaRepository<Tarefa,Long> {
    List<Tarefa> findByColaborador_Id(Long id);

    Tarefa findTarefaByTituloAndDescricaoAndColaborador_id(String titulo, String descricao, Long colaboradorId);
}
