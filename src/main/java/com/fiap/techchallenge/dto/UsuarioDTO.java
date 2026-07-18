package com.fiap.techchallenge.dto;

import com.fiap.techchallenge.constant.ConstantesValidacao;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UsuarioDTO(
        @Size(min = 3, max = 100, message = ConstantesValidacao.NOME_TAMANHO)
        String nome,
        @Size(min = 3, max = 50, message = ConstantesValidacao.LOGIN_TAMANHO)
        String login,
        @Email(message = ConstantesValidacao.EMAIL_INVALIDO)
        String email,
        EnderecoDTO endereco,
        String role
) {
}
