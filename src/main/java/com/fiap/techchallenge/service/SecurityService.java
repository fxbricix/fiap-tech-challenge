package com.fiap.techchallenge.service;

import com.fiap.techchallenge.dto.LoginUsuarioDTO;
import com.fiap.techchallenge.dto.TrocaSenhaUsuarioDTO;
import com.fiap.techchallenge.exception.NotAuthorizedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    private PasswordEncoder passwordEncoder;

    public SecurityService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public String criptografarSenha(String senha) {
        return passwordEncoder.encode(senha);
    }

    public Object login(LoginUsuarioDTO usuarioDTO, String senhaSalva) {
        if (compararSenha(usuarioDTO.senha(), senhaSalva)) {
            return "Login bem-sucedido";
        } else {
            throw new NotAuthorizedException("Senha incorreta");
        }
    }

    public boolean compararSenha(String senhaInformada, String senhaSalva){
        return passwordEncoder.matches(senhaInformada, senhaSalva);
    }
}
