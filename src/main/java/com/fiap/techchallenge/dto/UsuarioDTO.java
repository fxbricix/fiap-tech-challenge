package com.fiap.techchallenge.dto;

public record UsuarioDTO(
        String nome,
        String email,
        EnderecoDTO endereco,
        String role
) {
}
