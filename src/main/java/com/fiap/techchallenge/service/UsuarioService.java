package com.fiap.techchallenge.service;

import com.fiap.techchallenge.dto.CriarUsuarioDTO;
import com.fiap.techchallenge.dto.TrocaSenhaUsuarioDTO;
import com.fiap.techchallenge.dto.UsuarioDTO;
import com.fiap.techchallenge.entity.UsuarioEntity;
import com.fiap.techchallenge.exception.NotFoundException;
import com.fiap.techchallenge.mapper.ConsultaUsuarioMapper;
import com.fiap.techchallenge.mapper.CriarUsuarioMapper;
import com.fiap.techchallenge.mapper.AtualizarUsuarioMapper;
import com.fiap.techchallenge.repository.UsuarioJpaRepository;
import com.fiap.techchallenge.repository.UsuarioRepositoryImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepositoryImpl usuarioRepository;
    private final SecurityService securityService;
    private final UsuarioValidador usuarioValidador;

    public UsuarioService(UsuarioRepositoryImpl usuarioRepository, SecurityService securityService, UsuarioValidador usuarioValidador) {
        this.usuarioRepository = usuarioRepository;
        this.securityService = securityService;
        this.usuarioValidador = usuarioValidador;
    }


    @Transactional(readOnly = true)
    public List<UsuarioDTO> buscaPorNome(String nome) {
        List<UsuarioEntity> encontrados = usuarioRepository.buscaPorNome(nome);
        if (encontrados == null || encontrados.isEmpty()) {
            throw new NotFoundException("Usuário não encontrado");
        }
        return encontrados.stream()
                .map(ConsultaUsuarioMapper::toDTO)
                .toList();
    }

    @Transactional
    public void criarUsuario(CriarUsuarioDTO usuarioDTO) {
        usuarioValidador.validarCadastro(usuarioDTO);
        usuarioDTO.setSenha(securityService.criptografarSenha(usuarioDTO.getSenha()));
        usuarioRepository.salvar(CriarUsuarioMapper.toEntity(usuarioDTO));
    }

    @Transactional(readOnly = true)
    public UsuarioEntity buscarUsuarioPorEmail(String email) {
        return usuarioRepository.buscaPorEmail(email)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
    }

    @Transactional(readOnly = true)
    public UsuarioEntity buscarUsuarioPorLogin(String login) {
        return usuarioRepository.buscaPorLogin(login)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
    }

    @Transactional
    public void atualizarUsuario(UsuarioDTO usuarioDTO) {
        usuarioValidador.validarAtualizacao(usuarioDTO);

        usuarioRepository.buscaPorLogin(usuarioDTO.login())
                .ifPresentOrElse(usuario -> {
                    AtualizarUsuarioMapper.merge(usuarioDTO, usuario);
                    usuarioRepository.salvar(usuario);
                }, () -> {
                    throw new NotFoundException("Usuário não encontrado");
                });
    }

    @Transactional
    public void atualizarSenha(TrocaSenhaUsuarioDTO trocaSenhaUsuarioDTO){

        UsuarioEntity usuario = usuarioRepository
                .buscaPorLogin(trocaSenhaUsuarioDTO.login())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

        usuarioValidador.validarSenhaAtual(trocaSenhaUsuarioDTO, usuario);

        usuario.setSenhaHash(securityService.criptografarSenha(trocaSenhaUsuarioDTO.novaSenha()));
        usuario.setAtualizadoEm(Instant.now());
        usuarioRepository.salvar(usuario);
        var usuarioSalvo = usuarioRepository.buscaPorLogin(trocaSenhaUsuarioDTO.login());
    }
}

