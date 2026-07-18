package com.fiap.techchallenge.swagger;

import com.fiap.techchallenge.dto.LoginUsuarioDTO;
import com.fiap.techchallenge.dto.TrocaSenhaUsuarioDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Auth", description = "Endpoints relacionados a login e controle de senhas")
public interface AuthControllerSwagger {
    @PostMapping("/login")
    ResponseEntity<Object> logar(@RequestBody @Valid LoginUsuarioDTO usuarioDTO);

    @PatchMapping("/trocar-senha")
    ResponseEntity<Object> trocarSenha(@RequestBody @Valid TrocaSenhaUsuarioDTO usuarioDTO);

}
