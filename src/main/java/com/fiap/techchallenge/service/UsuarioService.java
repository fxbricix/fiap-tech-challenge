package com.fiap.techchallenge.service;

import com.fiap.techchallenge.dto.CriarUsuarioDTO;
import com.fiap.techchallenge.dto.UsuarioDTO;
import com.fiap.techchallenge.mapper.ConsultaUsuarioMapper;
import com.fiap.techchallenge.mapper.CriarUsuarioMapper;
import com.fiap.techchallenge.repository.UsuarioJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioJpaRepository usuarioJpaRepository;
    private final SecurityService securityService;

    public UsuarioService(UsuarioJpaRepository usuarioJpaRepository, SecurityService securityService) {
        this.usuarioJpaRepository = usuarioJpaRepository;
        this.securityService = securityService;
    }

    @Transactional(readOnly = true)
    public Optional<UsuarioDTO> buscaPorNome(String nome) {
        return Optional.ofNullable(usuarioJpaRepository.findByNomeUsuario(nome))
                .map(ConsultaUsuarioMapper::toDTO);
    }

    @Transactional
    public void criarUsuario(CriarUsuarioDTO usuarioDTO) {
        usuarioDTO.setSenha(securityService.criptografarSenha(usuarioDTO.getSenha()));
        if (usuarioJpaRepository.existsByEmail(usuarioDTO.getEmail())) {
            throw new IllegalArgumentException("Usuário com este email já existe");
        }
        usuarioJpaRepository.save(CriarUsuarioMapper.toEntity(usuarioDTO));
    }
}

