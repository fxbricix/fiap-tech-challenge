package com.fiap.techchallenge.dto;

import com.fiap.techchallenge.constant.ConstantesValidacao;
import jakarta.validation.constraints.NotBlank;

public record LoginUsuarioDTO(
    @NotBlank(message = ConstantesValidacao.LOGIN_OBRIGATORIO)
    String login,
    @NotBlank(message = ConstantesValidacao.SENHA_OBRIGATORIA)
    String senha
) {
}
