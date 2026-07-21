package com.fiap.techchallenge.repository;

import com.fiap.techchallenge.entity.UsuarioEntity;

import java.util.List;
import java.util.Optional;

public interface IUsuarioRepository {
    UsuarioEntity salvar(UsuarioEntity usuarioEntity);
    List<UsuarioEntity> buscaPorNome(String nomeUsuario);
    boolean existePorEmail(String email);
    Optional<UsuarioEntity> buscaPorLogin(String login);
    boolean existePorLogin(String login);
}

