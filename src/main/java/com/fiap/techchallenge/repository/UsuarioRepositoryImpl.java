package com.fiap.techchallenge.repository;

import com.fiap.techchallenge.entity.UsuarioEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepositoryImpl implements IUsuarioRepository{

    private final UsuarioJpaRepository repository;

    public UsuarioRepositoryImpl(UsuarioJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public UsuarioEntity salvar(UsuarioEntity usuarioEntity) {
        return repository.save(usuarioEntity);
    }

    @Override
    public List<UsuarioEntity> buscaPorNome(String nomeUsuario) {
        return repository.findByNomeUsuarioContainingIgnoreCase(nomeUsuario);
    }

    @Override
    public boolean existePorEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public Optional<UsuarioEntity> buscaPorLogin(String login) {
        return repository.findByLogin(login);
    }

    @Override
    public boolean existePorLogin(String login) {
        return repository.existsByLogin(login);
    }

    @Override
    public void apagarPorLogin(String login) {
        repository.deleteByLogin(login);
    }

}
