package com.fiap.techchallenge.service;

import com.fiap.techchallenge.dto.CriarUsuarioDTO;
import com.fiap.techchallenge.dto.UsuarioDTO;
import com.fiap.techchallenge.repository.UsuarioRepositoryImpl;

public class UsuarioValidador {

    private final UsuarioRepositoryImpl usuarioRepository;

    public UsuarioValidador(UsuarioRepositoryImpl usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void validarCadastro(CriarUsuarioDTO usuarioDTO) {
        if (usuarioRepository.existePorEmail(usuarioDTO.getEmail())) {
            throw new IllegalArgumentException("Usuário com este email já existe");
        }
        if (usuarioDTO.getLogin() != null && usuarioRepository.existePorLogin(usuarioDTO.getLogin())) {
            throw new IllegalArgumentException("Usuário com este login já existe");
        }
    }

    public void validarAtualizacao(UsuarioDTO usuarioDTO) {
        if (usuarioDTO.login() == null || usuarioDTO.login().isBlank()) {
            throw new IllegalArgumentException("É necessário informar um login para atualizar o cadastro");
        }
    }
}

