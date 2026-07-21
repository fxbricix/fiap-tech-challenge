package com.fiap.techchallenge.service;

import com.fiap.techchallenge.dto.CriarUsuarioDTO;
import com.fiap.techchallenge.dto.TrocaSenhaUsuarioDTO;
import com.fiap.techchallenge.entity.UsuarioEntity;
import com.fiap.techchallenge.exception.UserValidationException;
import com.fiap.techchallenge.repository.UsuarioRepositoryImpl;
import org.springframework.stereotype.Component;

@Component
public class UsuarioValidador {

    private final UsuarioRepositoryImpl usuarioRepository;
    private final SecurityService securityService;

    public UsuarioValidador(UsuarioRepositoryImpl usuarioRepository, SecurityService securityService) {
        this.usuarioRepository = usuarioRepository;
        this.securityService = securityService;
    }

    public void validarCadastro(CriarUsuarioDTO usuarioDTO) {
        validarEmailDuplicado(usuarioDTO.getEmail());
        if (usuarioDTO.getLogin() != null && usuarioRepository.existePorLogin(usuarioDTO.getLogin())) {
            throw new UserValidationException("Usuário com este login já existe");
        }
    }

    public void validarSenhaAtual(TrocaSenhaUsuarioDTO dto, UsuarioEntity entity) {
        if (!securityService.compararSenha(dto.senhaAtual(), entity.getSenhaHash())) {
            throw new UserValidationException("Senha atual incorreta");
        }
    }

    public void validarEmailDuplicado(String email) {
        if (usuarioRepository.existePorEmail(email)) {
            throw new UserValidationException("Usuário com este email já existe");
        }
    }
}

