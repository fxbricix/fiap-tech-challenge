package com.fiap.techchallenge.dto;

import com.fiap.techchallenge.constant.ConstantesValidacao;
import jakarta.validation.constraints.NotBlank;

public record TrocaSenhaUsuarioDTO(
        @NotBlank(message = ConstantesValidacao.SENHA_ATUAL_OBRIGATORIA)
        String senhaAtual,
        @NotBlank(message = ConstantesValidacao.NOVA_SENHA_OBRIGATORIA)
        String novaSenha
) {
}
