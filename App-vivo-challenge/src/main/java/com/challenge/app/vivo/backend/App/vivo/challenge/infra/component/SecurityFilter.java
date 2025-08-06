package com.challenge.app.vivo.backend.App.vivo.challenge.infra.component;

import com.challenge.app.vivo.backend.App.vivo.challenge.domain.Usuario;
import com.challenge.app.vivo.backend.App.vivo.challenge.repository.UsuarioRepository;
import com.challenge.app.vivo.backend.App.vivo.challenge.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = recuperaToken(request);

        if (token != null){
            String email = tokenService.verificarToken(token);
            Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Erro ao encontrar o usuário"));
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario,null);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request,response);
    }

    private String recuperaToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization != null){
            return authorization.replace("Bearer","");
        }
        return null;
    }
}
