package com.fiap.techchallenge.service;

import com.fiap.techchallenge.entity.UsuarioDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsService implements UserDetailsService {
    private final UsuarioService usuarioService;

    public UsuarioDetailsService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public UsuarioDetails loadUserByUsername(String username) {
        var usuarioEntity = usuarioService.buscarUsuarioPorLogin(username);
        return new UsuarioDetails(usuarioEntity);
    }
}

