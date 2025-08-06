package com.challenge.app.vivo.backend.App.vivo.challenge.controller;

import com.challenge.app.vivo.backend.App.vivo.challenge.domain.Tarefa;
import com.challenge.app.vivo.backend.App.vivo.challenge.domain.Usuario;
import com.challenge.app.vivo.backend.App.vivo.challenge.dto.TarefaDTO;
import com.challenge.app.vivo.backend.App.vivo.challenge.dto.TarefaFinalizadaDTO;
import com.challenge.app.vivo.backend.App.vivo.challenge.repository.TarefaRepository;
import com.challenge.app.vivo.backend.App.vivo.challenge.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    public UsuarioRepository usuarioRepository;

    @Autowired
    public TarefaRepository tarefaRepository;

    @PostMapping("/{id}")
    public ResponseEntity<Tarefa> criarTarefa(@PathVariable Long id, @RequestBody TarefaDTO dto){
        Usuario gestor = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Erro ao encontrar o gestor pelo ID"));
        Usuario colaborador = usuarioRepository.findByNome(dto.nomeColaborador()).orElseThrow(() -> new RuntimeException("Erro ao encontrar o colaborador pelo nome"));
        Tarefa tarefa = new Tarefa(dto.titulo(), dto.descricao(), dto.dataInicio(), dto.dataTermino(),colaborador,gestor);
        return ResponseEntity.ok(tarefa);
    }


    @GetMapping("/{id}")
    public List<Tarefa> listarTarefas(@PathVariable Long id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if(usuario.isPresent()){
            Usuario colaborador = usuario.get();
            List<Tarefa> tarefas = tarefaRepository.findByColaborador_Id(colaborador.getId());
            List<Tarefa> tarefasAtivas = tarefas.stream().filter(Tarefa::isAtivo).toList();
            return tarefasAtivas;
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity finalizarTarefa(@PathVariable Long id, @RequestBody TarefaFinalizadaDTO dto){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()){
            Usuario colaborador = usuario.get();
            Tarefa tarefa = tarefaRepository.findTarefaByTituloAndDescricaoAndColaborador_id(dto.titulo(), dto.descricao(), colaborador.getId());
            return ResponseEntity.ok(tarefa);
        }
        return ResponseEntity.ok(500);
    }




}
