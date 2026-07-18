package com.fiap.techchallenge.dto;

public record TrocaSenhaUsuarioDTO(
        String login,
        String senhaAtual,
        String novaSenha
) {
}
