package com.fiap.techchallenge.repository;

import com.fiap.techchallenge.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity, Integer>{
    public UsuarioEntity findByNomeUsuario(String nomeUsuario);
    public UsuarioEntity findByEmail(String email);
}
