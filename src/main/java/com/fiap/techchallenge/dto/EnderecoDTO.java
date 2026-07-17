package com.fiap.techchallenge.dto;

import jakarta.validation.constraints.NotBlank;

public record EnderecoDTO (
        @NotBlank
        String rua,
        @NotBlank
        String numero,
        @NotBlank
        String complemento,
        @NotBlank
        String bairro,
        @NotBlank
        String cidade,
        @NotBlank
        String estado,
        @NotBlank
        String cep
){
}
