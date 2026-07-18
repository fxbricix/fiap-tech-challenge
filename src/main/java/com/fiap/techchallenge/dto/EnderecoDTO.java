package com.fiap.techchallenge.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

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
        @Size(min = 2, max = 2, message = "O estado deve ter exatamente 2 caracteres")
        String estado,
        @NotBlank
        @Size(min = 8, max = 8, message = "O CEP deve ter exatamente 8 caracteres")
        String cep
){
}
