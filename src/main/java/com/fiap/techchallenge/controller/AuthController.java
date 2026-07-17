package com.fiap.techchallenge.controller;

import com.fiap.techchallenge.dto.CriarUsuarioDTO;
import com.fiap.techchallenge.dto.LoginUsuarioDTO;
import com.fiap.techchallenge.dto.UsuarioDTO;
import com.fiap.techchallenge.service.SecurityService;
import com.fiap.techchallenge.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    private UsuarioService usuarioService;
    private SecurityService securityService;

    public AuthController(UsuarioService usuarioService, SecurityService securityService) {
        this.usuarioService = usuarioService;
        this.securityService = securityService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioDTO> cadastrar(@RequestBody @Valid CriarUsuarioDTO usuarioDTO) {
        usuarioService.criarUsuario(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<Object> logar(@RequestBody @Valid LoginUsuarioDTO usuarioDTO) {
        var consulta = usuarioService.buscarUsuarioPorEmail(usuarioDTO.email());
        var login = securityService.login(usuarioDTO, consulta.getSenhaHash());
        return ResponseEntity.ok(login);
    }
}
