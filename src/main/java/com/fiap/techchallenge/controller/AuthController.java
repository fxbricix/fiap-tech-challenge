package com.fiap.techchallenge.controller;

import com.fiap.techchallenge.dto.LoginUsuarioDTO;
import com.fiap.techchallenge.dto.TrocaSenhaUsuarioDTO;
import com.fiap.techchallenge.service.SecurityService;
import com.fiap.techchallenge.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    private UsuarioService usuarioService;
    private SecurityService securityService;

    public AuthController(UsuarioService usuarioService, SecurityService securityService) {
        this.usuarioService = usuarioService;
        this.securityService = securityService;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> logar(@RequestBody @Valid LoginUsuarioDTO usuarioDTO) {
        var consulta = usuarioService.buscarUsuarioPorLogin(usuarioDTO.login());
        var login = securityService.login(usuarioDTO, consulta.getSenhaHash());
        return ResponseEntity.ok(login);
    }

    @PatchMapping("/trocar-senha")
    public ResponseEntity<Object> trocarSenha(@RequestBody @Valid TrocaSenhaUsuarioDTO usuarioDTO) {
        usuarioService.atualizarSenha(usuarioDTO);
        return ResponseEntity.ok("Senha alterada com sucesso");
    }
}
