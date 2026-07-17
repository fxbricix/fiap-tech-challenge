package com.fiap.techchallenge.service;

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

    public boolean compararSenha(String senhaInformada, String senhaSalva){
        return passwordEncoder.matches(senhaInformada, senhaSalva);
    }
}
