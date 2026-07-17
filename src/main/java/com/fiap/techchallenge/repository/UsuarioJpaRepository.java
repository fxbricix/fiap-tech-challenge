package com.fiap.techchallenge.repository;

import com.fiap.techchallenge.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity, Integer>{
    public Optional<List<UsuarioEntity>> findByNomeUsuario(String nomeUsuario);
    public Optional<UsuarioEntity> findByEmail(String email);
    public boolean existsByEmail(String email);
}
