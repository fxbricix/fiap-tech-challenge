package com.fiap.techchallenge.mapper;

import com.fiap.techchallenge.dto.CriarUsuarioDTO;
import com.fiap.techchallenge.entity.EnderecoEntity;
import com.fiap.techchallenge.entity.RoleEntity;
import com.fiap.techchallenge.entity.UsuarioEntity;

import java.time.Instant;

public final class CriarUsuarioMapper {

    private CriarUsuarioMapper() {}


    public static UsuarioEntity toEntity(CriarUsuarioDTO dto) {
        if (dto == null) return null;
        UsuarioEntity u = new UsuarioEntity();
        u.setNomeUsuario(dto.getNome());
        u.setEmail(dto.getEmail());
        u.setSenhaHash(dto.getSenha());
        Instant now = Instant.now();
        u.setCriadoEm(now);
        u.setAtualizadoEm(now);

        RoleEntity role = new RoleEntity();
        role.setId(dto.getRole().getId());
        u.setRole(role);

        EnderecoEntity endereco = EnderecoMapper.toEntity(dto.getEnderecoDTO());
        if (endereco != null) {
            endereco.setUsuario(u);
            u.setEndereco(endereco);
        }
        return u;
    }
}