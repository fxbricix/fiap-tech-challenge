package com.fiap.techchallenge.controller;

import com.fiap.techchallenge.dto.UsuarioDTO;
import com.fiap.techchallenge.service.UsuarioService;
import com.fiap.techchallenge.swagger.UsuarioControllerSwager;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/usuarios")
@Slf4j
@Validated
public class UsuarioController implements UsuarioControllerSwager {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> buscaUsuarioPorNome(@RequestParam @NotBlank String nome) {
        return ResponseEntity.ok(usuarioService.buscaPorNome(nome));
    }

    @PatchMapping("/atualizar")
    public ResponseEntity<Void> atualizarInformacoes(@RequestBody UsuarioDTO usuarioDTO) {
        // TODO : USUARIO SO PODER ATUALIZER SEU PROPRIO CADASTRO OU DONO ATUALIZAR TUDO
        usuarioService.atualizarUsuario(usuarioDTO);
        return ResponseEntity.noContent().build();
    }
}
