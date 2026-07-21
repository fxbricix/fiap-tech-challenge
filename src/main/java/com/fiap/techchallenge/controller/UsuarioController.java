package com.fiap.techchallenge.controller;

import com.fiap.techchallenge.dto.UsuarioDTO;
import com.fiap.techchallenge.service.SecurityService;
import com.fiap.techchallenge.service.UsuarioService;
import com.fiap.techchallenge.swagger.UsuarioControllerSwager;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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
    @PreAuthorize("hasRole('ROLE_DONO')")
    public ResponseEntity<List<UsuarioDTO>> buscaUsuarioPorNome(@RequestParam @NotBlank String nome) {
        return ResponseEntity.ok(usuarioService.buscaPorNome(nome));
    }

    @PatchMapping("/me")
    public ResponseEntity<UsuarioDTO> atualizarInformacoes(Authentication authentication, @RequestBody UsuarioDTO usuarioDTO) {
        var retorno = usuarioService.atualizarUsuario(authentication.getName(), usuarioDTO);
        return ResponseEntity.ok(retorno);
    }
}
