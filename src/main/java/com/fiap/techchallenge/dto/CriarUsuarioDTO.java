package com.fiap.techchallenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.techchallenge.constant.ConstantesValidacao;
import com.fiap.techchallenge.entity.RoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CriarUsuarioDTO {

    @NotBlank(message = ConstantesValidacao.NOME_USUARIO_VAZIO)
    private String nome;

    @NotBlank(message = ConstantesValidacao.EMAIL_INVALIDO)
    @Email(message = ConstantesValidacao.EMAIL_INVALIDO)
    private String email;

    @NotBlank(message = ConstantesValidacao.LOGIN_INVALIDO)
    @Size(min = 3, max = 50, message = ConstantesValidacao.LOGIN_TAMANHO)
    private String login;

    @NotBlank(message = ConstantesValidacao.SENHA_VAZIA)
    private String senha;

    @NotNull(message = ConstantesValidacao.ROLE_NULA)
    private RoleEnum role;

    @NotNull(message = ConstantesValidacao.ENDERECO_NULO)
    @JsonProperty("endereco")
    private EnderecoDTO enderecoDTO;
}

