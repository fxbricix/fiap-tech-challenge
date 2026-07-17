package com.fiap.techchallenge.dto;

public record TrocaSenhaUsuarioDTO(
        String email,
        String senhaAtual,
        String novaSenha
) {
}
