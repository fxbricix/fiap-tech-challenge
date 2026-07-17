package com.fiap.techchallenge.controller;

import com.fiap.techchallenge.dto.UsuarioDTO;
import com.fiap.techchallenge.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/usuarios")
@Slf4j
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/{nome}")
    public ResponseEntity<List<UsuarioDTO>> buscaUsuarioPorNome(@PathVariable String nome) {
        return ResponseEntity.ok(usuarioService.buscaPorNome(nome));
    }


}
