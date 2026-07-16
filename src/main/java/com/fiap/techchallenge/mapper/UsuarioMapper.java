package com.fiap.techchallenge.mapper;

import com.fiap.techchallenge.dto.UsuarioDTO;
import com.fiap.techchallenge.entity.EnderecoEntity;
import com.fiap.techchallenge.entity.UsuarioEntity;

public final class UsuarioMapper {

    private UsuarioMapper() {}

    public static UsuarioDTO toDTO(UsuarioEntity u) {
        if (u == null) return null;
        return new UsuarioDTO(
                u.getNomeUsuario(),
                u.getEmail(),
                EnderecoMapper.toDTO(u.getEndereco()),
                u.getRole().getNome()
        );
    }

    public static UsuarioEntity toEntity(UsuarioDTO dto) {
        if (dto == null) return null;
        UsuarioEntity u = new UsuarioEntity();
        u.setNomeUsuario(dto.nome());
        u.setEmail(dto.email());
        // todo senha hash
        EnderecoEntity endereco = EnderecoMapper.toEntity(dto.endereco());
        if (endereco != null) {
            endereco.setUsuario(u);
            u.setEndereco(endereco);
        }        return u;
    }
}