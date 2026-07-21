package com.fiap.techchallenge.service;

import com.fiap.techchallenge.dto.LoginUsuarioDTO;
import com.fiap.techchallenge.entity.UsuarioDetails;
import com.fiap.techchallenge.exception.NotAuthorizedException;
import com.fiap.techchallenge.exception.NotFoundException;
import com.fiap.techchallenge.repository.UsuarioRepositoryImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepositoryImpl repository;
    private final JwtService jwtService;

    public SecurityService(PasswordEncoder passwordEncoder, UsuarioRepositoryImpl repository, JwtService jwtService) {
        this.passwordEncoder = passwordEncoder;
        this.repository = repository;
        this.jwtService = jwtService;
    }

    public String criptografarSenha(String senha) {
        return passwordEncoder.encode(senha);
    }

    public boolean compararSenha(String senhaInformada, String senhaSalva){
        return passwordEncoder.matches(senhaInformada, senhaSalva);
    }

    public String logar(LoginUsuarioDTO dto){
        var consulta = repository.buscaPorLogin(dto.login())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
        
        if (!compararSenha(dto.senha(), consulta.getSenhaHash())) {
            throw new NotAuthorizedException("Credenciais inválidas");
        }
        var userDetails = new UsuarioDetails(consulta);

        return jwtService.gerarToken(userDetails);
    }
}
