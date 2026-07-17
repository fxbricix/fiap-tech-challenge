package com.fiap.techchallenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.techchallenge.entity.RoleEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CriarUsuarioDTO {

    @NotBlank(message = "Nome do usuário não pode ser vazio")
    private String nome;

    @NotBlank
    private String email;

    @NotBlank
    private String senha;

    @NotNull(message = "Role não pode ser nula")
    private RoleEnum role;

    @NotNull
    @JsonProperty("endereco")
    private EnderecoDTO enderecoDTO;
}

