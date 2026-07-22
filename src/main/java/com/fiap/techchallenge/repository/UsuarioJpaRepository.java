package com.fiap.techchallenge.repository;

import com.fiap.techchallenge.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity, Integer>{
    List<UsuarioEntity> findByNomeUsuarioContainingIgnoreCase(String nomeUsuario);
    Optional<UsuarioEntity> findByEmail(String email);
    boolean existsByEmail(String email);
    @Query("select u from UsuarioEntity u left join fetch u.role where u.login = :login")
    Optional<UsuarioEntity> findByLogin(@Param("login") String login);
    boolean existsByLogin(String login);
    void deleteByLogin(String login);
}
