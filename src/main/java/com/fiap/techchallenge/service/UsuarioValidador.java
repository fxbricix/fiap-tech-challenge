package com.fiap.techchallenge.service;

import com.fiap.techchallenge.dto.CriarUsuarioDTO;
import com.fiap.techchallenge.dto.TrocaSenhaUsuarioDTO;
import com.fiap.techchallenge.dto.UsuarioDTO;
import com.fiap.techchallenge.entity.UsuarioEntity;
import com.fiap.techchallenge.repository.UsuarioRepositoryImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class UsuarioValidador {

    private final UsuarioRepositoryImpl usuarioRepository;
    private final SecurityService securityService;

    public UsuarioValidador(UsuarioRepositoryImpl usuarioRepository, SecurityService securityService) {
        this.usuarioRepository = usuarioRepository;
        this.securityService = securityService;
    }

    public void validarCadastro(CriarUsuarioDTO usuarioDTO) {
        if (usuarioRepository.existePorEmail(usuarioDTO.getEmail())) {
            throw new IllegalArgumentException("Usuário com este email já existe");
        }
        if (usuarioDTO.getLogin() != null && usuarioRepository.existePorLogin(usuarioDTO.getLogin())) {
            throw new IllegalArgumentException("Usuário com este login já existe");
        }
    }

    public void validarSenhaAtual(TrocaSenhaUsuarioDTO dto, UsuarioEntity entity) {
        if (!securityService.compararSenha(dto.senhaAtual(), entity.getSenhaHash())) {
            throw new IllegalArgumentException("Senha atual incorreta");
        }
    }
}

