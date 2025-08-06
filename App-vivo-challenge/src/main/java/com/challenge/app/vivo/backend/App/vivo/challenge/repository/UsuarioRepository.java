package com.challenge.app.vivo.backend.App.vivo.challenge.repository;

import com.challenge.app.vivo.backend.App.vivo.challenge.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String username);

    Optional<Usuario> findByNome(String s);
}
