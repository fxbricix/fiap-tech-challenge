package com.fiap.techchallenge.service;

import com.fiap.techchallenge.dto.CriarUsuarioDTO;
import com.fiap.techchallenge.dto.UsuarioDTO;
import com.fiap.techchallenge.entity.UsuarioEntity;
import com.fiap.techchallenge.exception.NotFoundException;
import com.fiap.techchallenge.mapper.ConsultaUsuarioMapper;
import com.fiap.techchallenge.mapper.CriarUsuarioMapper;
import com.fiap.techchallenge.mapper.AtualizarUsuarioMapper;
import com.fiap.techchallenge.repository.UsuarioJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioJpaRepository usuarioJpaRepository;
    private final SecurityService securityService;

    public UsuarioService(UsuarioJpaRepository usuarioJpaRepository, SecurityService securityService) {
        this.usuarioJpaRepository = usuarioJpaRepository;
        this.securityService = securityService;
    }

    @Transactional(readOnly = true)
    public List<UsuarioDTO> buscaPorNome(String nome) {
        List<UsuarioEntity> encontrados = usuarioJpaRepository.findByNomeUsuarioContainingIgnoreCase(nome);
        if (encontrados == null || encontrados.isEmpty()) {
            throw new NotFoundException("Usuário não encontrado");
        }
        return encontrados.stream()
                .map(ConsultaUsuarioMapper::toDTO)
                .toList();
    }

    @Transactional
    public void criarUsuario(CriarUsuarioDTO usuarioDTO) {
        usuarioDTO.setSenha(securityService.criptografarSenha(usuarioDTO.getSenha()));
        if (usuarioJpaRepository.existsByEmail(usuarioDTO.getEmail())) {
            throw new IllegalArgumentException("Usuário com este email já existe");
        }
        usuarioJpaRepository.save(CriarUsuarioMapper.toEntity(usuarioDTO));
    }

    @Transactional(readOnly = true)
    public UsuarioEntity buscarUsuarioPorEmail(String email) {
        return usuarioJpaRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
    }

    @Transactional
    public void atualizarUsuario(UsuarioDTO usuarioDTO) {
        if (usuarioDTO.email().isBlank()) {
            throw new IllegalArgumentException("É necessário informar um e-mail para atualizar o cadastro");
        }

        usuarioJpaRepository.findByEmail(usuarioDTO.email())
                .ifPresentOrElse(usuario -> {
                    AtualizarUsuarioMapper.merge(usuarioDTO, usuario);
                    usuarioJpaRepository.save(usuario);
                }, () -> {
                    throw new NotFoundException("Usuário não encontrado");
                });
    }
}

