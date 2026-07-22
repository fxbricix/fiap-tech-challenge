package com.fiap.techchallenge.service;

import com.fiap.techchallenge.dto.CriarUsuarioDTO;
import com.fiap.techchallenge.dto.TrocaSenhaUsuarioDTO;
import com.fiap.techchallenge.dto.UsuarioDTO;
import com.fiap.techchallenge.entity.UsuarioEntity;
import com.fiap.techchallenge.exception.NotFoundException;
import com.fiap.techchallenge.mapper.ConsultaUsuarioMapper;
import com.fiap.techchallenge.mapper.CriarUsuarioMapper;
import com.fiap.techchallenge.mapper.AtualizarUsuarioMapper;
import com.fiap.techchallenge.repository.UsuarioRepositoryImpl;
import org.springframework.security.core.Authentication;
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
    public UsuarioEntity buscarUsuarioPorLogin(String login) {
        return usuarioRepository.buscaPorLogin(login)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
    }

    @Transactional
    public UsuarioDTO atualizarUsuario(String login, UsuarioDTO usuarioDTO) {
        UsuarioEntity usuario = usuarioRepository
                .buscaPorLogin(login)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

        AtualizarUsuarioMapper.merge(usuarioDTO, usuario);
        usuarioRepository.salvar(usuario);
        return ConsultaUsuarioMapper.toDTO(usuario);
    }

    @Transactional
    public void atualizarSenha(String login, TrocaSenhaUsuarioDTO trocaSenhaUsuarioDTO){

        UsuarioEntity usuario = usuarioRepository
                .buscaPorLogin(login)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

        usuarioValidador.validarSenhaAtual(trocaSenhaUsuarioDTO, usuario);

        usuario.setSenhaHash(securityService.criptografarSenha(trocaSenhaUsuarioDTO.novaSenha()));
        usuario.setAtualizadoEm(Instant.now());
        usuarioRepository.salvar(usuario);
    }

    @Transactional
    public void apagarProprioUsuario(Authentication authentication){
        if (!usuarioRepository.existePorLogin(authentication.getName())) {
            throw new NotFoundException("Usuário não encontrado");
        }
        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_DONO"))) {
            throw new IllegalArgumentException("O dono não pode apagar sua própria conta");
        }
        usuarioRepository.apagarPorLogin(authentication.getName());
    }
}

