package com.challenge.app.vivo.backend.App.vivo.challenge.controller;

import com.challenge.app.vivo.backend.App.vivo.challenge.domain.Usuario;
import com.challenge.app.vivo.backend.App.vivo.challenge.dto.UsuarioDTO;
import com.challenge.app.vivo.backend.App.vivo.challenge.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/cadstro")
    public ResponseEntity<Usuario> cadastro(@RequestBody UsuarioDTO dto){
        String senhaEncriptada = passwordEncoder.encode(dto.senha());
        Usuario usuario = new Usuario(dto.nome(),dto.email(),senhaEncriptada);
        usuarioRepository.save(usuario);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/{id}")
    public UsuarioDTO listarDadoUsuario(@PathVariable Long id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()){
            Usuario u = usuario.get();
            return new UsuarioDTO(u.getNome(),u.getEmail(),u.getSenha());
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarConta(@PathVariable Long id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if(usuario.isPresent()){
            Usuario u = usuario.get();
            u.deletarAtivo();
            return ResponseEntity.ok(500);
        }
        return ResponseEntity.ok(401);
    }


}
