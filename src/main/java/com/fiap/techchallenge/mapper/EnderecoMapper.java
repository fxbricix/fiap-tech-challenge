package com.fiap.techchallenge.mapper;

import com.fiap.techchallenge.dto.EnderecoDTO;
import com.fiap.techchallenge.entity.EnderecoEntity;

public final class EnderecoMapper {

    private EnderecoMapper() {}

    public static EnderecoDTO toDTO(EnderecoEntity e) {
        if (e == null) return null;
        return new EnderecoDTO(
                e.getRua(),
                e.getNumero(),
                e.getComplemento(),
                e.getBairro(),
                e.getCidade(),
                e.getEstado(),
                e.getCep()
        );
    }

    public static EnderecoEntity toEntity(EnderecoDTO dto) {
        if (dto == null) return null;
        EnderecoEntity e = new EnderecoEntity();
        e.setRua(dto.rua());
        e.setNumero(dto.numero());
        e.setComplemento(dto.complemento());
        e.setBairro(dto.bairro());
        e.setCidade(dto.cidade());
        e.setEstado(dto.estado());
        e.setCep(dto.cep());
        return e;
    }
}