package com.fiap.techchallenge.service;

import com.fiap.techchallenge.dto.UsuarioDTO;
import com.fiap.techchallenge.mapper.UsuarioMapper;
import com.fiap.techchallenge.repository.UsuarioJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioJpaRepository usuarioJpaRepository;

    public UsuarioService(UsuarioJpaRepository usuarioJpaRepository) {
        this.usuarioJpaRepository = usuarioJpaRepository;
    }

    @Transactional(readOnly = true)
    public Optional<UsuarioDTO> buscaPorNome(String nome) {
        return Optional.ofNullable(usuarioJpaRepository.findByNomeUsuario(nome))
                .map(UsuarioMapper::toDTO);
    }
}

