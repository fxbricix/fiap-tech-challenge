package com.fiap.techchallenge.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UsuarioDTO(
        @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
        String nome,
        @Size(min = 3, max = 50, message = "O login deve ter entre 3 e 50 caracteres")
        String login,
        @Email(message = "O email deve ser válido")
        String email,
        EnderecoDTO endereco,
        String role
) {
}
