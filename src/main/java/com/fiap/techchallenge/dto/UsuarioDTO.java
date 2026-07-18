package com.fiap.techchallenge.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fiap.techchallenge.constant.ConstantesValidacao;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UsuarioDTO(
        @Size(min = 3, max = 100, message = ConstantesValidacao.NOME_TAMANHO)
        String nome,
        @Email(message = ConstantesValidacao.EMAIL_INVALIDO)
        String email,
        EnderecoDTO endereco,
        String role
) {
}
