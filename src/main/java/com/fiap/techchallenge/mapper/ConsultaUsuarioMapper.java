package com.fiap.techchallenge.mapper;

import com.fiap.techchallenge.dto.UsuarioDTO;
import com.fiap.techchallenge.entity.UsuarioEntity;

public final class ConsultaUsuarioMapper {

    private ConsultaUsuarioMapper() {}

    public static UsuarioDTO toDTO(UsuarioEntity u) {
        if (u == null) return null;
        return new UsuarioDTO(
                u.getNomeUsuario(),
                u.getLogin(),
                u.getEmail(),
                EnderecoMapper.toDTO(u.getEndereco()),
                u.getRole().getNome()
        );
    }
}