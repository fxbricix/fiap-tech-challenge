package com.fiap.techchallenge.mapper;

import com.fiap.techchallenge.dto.UsuarioDTO;
import com.fiap.techchallenge.entity.EnderecoEntity;
import com.fiap.techchallenge.entity.UsuarioEntity;

import java.time.Instant;

public final class AtualizarUsuarioMapper {

    private AtualizarUsuarioMapper() {}

    public static void merge(UsuarioDTO dto, UsuarioEntity entity) {
        if (dto == null || entity == null) return;

        if (dto.nome() != null && !dto.nome().isBlank()) {
            entity.setNomeUsuario(dto.nome());
        }
        if (dto.email() != null && !dto.email().isBlank()) {
            entity.setEmail(dto.email());
        }

        if (dto.endereco() != null) {
            EnderecoEntity current = entity.getEndereco();
            if (current == null) {
                EnderecoEntity novo = EnderecoMapper.toEntity(dto.endereco());
                if (novo != null) {
                    novo.setUsuario(entity);
                    entity.setEndereco(novo);
                }
            } else {
                var d = dto.endereco();
                if (d.rua() != null) current.setRua(d.rua());
                if (d.numero() != null) current.setNumero(d.numero());
                if (d.complemento() != null) current.setComplemento(d.complemento());
                if (d.bairro() != null) current.setBairro(d.bairro());
                if (d.cidade() != null) current.setCidade(d.cidade());
                if (d.estado() != null) current.setEstado(d.estado());
                if (d.cep() != null) current.setCep(d.cep());
            }
        }

        entity.setAtualizadoEm(Instant.now());
    }
}
