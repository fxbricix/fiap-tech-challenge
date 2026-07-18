package com.fiap.techchallenge.service;

import com.fiap.techchallenge.dto.LoginUsuarioDTO;
import com.fiap.techchallenge.dto.UsuarioDTO;
import com.fiap.techchallenge.entity.UsuarioDetails;
import com.fiap.techchallenge.exception.NotAuthorizedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    private PasswordEncoder passwordEncoder;
    private UsuarioService usuarioService;

    public SecurityService(PasswordEncoder passwordEncoder, UsuarioService usuarioService) {
        this.passwordEncoder = passwordEncoder;
        this.usuarioService = usuarioService;
    }

    public String criptografarSenha(String senha) {
        return passwordEncoder.encode(senha);
    }

    public boolean compararSenha(String senhaInformada, String senhaSalva){
        return passwordEncoder.matches(senhaInformada, senhaSalva);
    }

    public String logar(LoginUsuarioDTO dto){
        var consulta = usuarioService.buscarUsuarioPorLogin(dto.login());
        if (!compararSenha(dto.senha(), consulta.getSenhaHash())) {
            throw new NotAuthorizedException("Credenciais inválidas");
        }
        var userDetails = new UsuarioDetails(consulta);

        return jwtService.gerarToken(userDetails);;
    }
}
