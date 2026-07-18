package com.fiap.techchallenge.dto;

import com.fiap.techchallenge.constant.ConstantesValidacao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EnderecoDTO (
        @NotBlank(message = ConstantesValidacao.RUA_OBRIGATORIA)
        String rua,
        @NotBlank(message = ConstantesValidacao.NUMERO_OBRIGATORIO)
        String numero,
        @NotBlank(message = ConstantesValidacao.COMPLEMENTO_OBRIGATORIO)
        String complemento,
        @NotBlank(message = ConstantesValidacao.BAIRRO_OBRIGATORIO)
        String bairro,
        @NotBlank(message = ConstantesValidacao.CIDADE_OBRIGATORIA)
        String cidade,
        @NotBlank(message = ConstantesValidacao.ESTADO_INVALIDO)
        @Size(min = 2, max = 2, message = ConstantesValidacao.ESTADO_INVALIDO)
        String estado,
        @NotBlank(message = ConstantesValidacao.CEP_INVALIDO)
        @Size(min = 8, max = 8, message = ConstantesValidacao.CEP_INVALIDO)
        String cep
){
}
