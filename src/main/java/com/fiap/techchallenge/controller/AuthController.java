package com.fiap.techchallenge.controller;

import com.fiap.techchallenge.dto.CriarUsuarioDTO;
import com.fiap.techchallenge.dto.LoginUsuarioDTO;
import com.fiap.techchallenge.dto.TrocaSenhaUsuarioDTO;
import com.fiap.techchallenge.dto.UsuarioDTO;
import com.fiap.techchallenge.exception.NotAuthorizedException;
import com.fiap.techchallenge.service.SecurityService;
import com.fiap.techchallenge.service.UsuarioService;
import com.fiap.techchallenge.service.JwtService;
import com.fiap.techchallenge.entity.UsuarioDetails;
import java.util.Map;
import com.fiap.techchallenge.swagger.AuthControllerSwagger;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1/auth")
public class AuthController implements AuthControllerSwagger {

    private UsuarioService usuarioService;
    private SecurityService securityService;

    public AuthController(UsuarioService usuarioService, SecurityService securityService) {
        this.usuarioService = usuarioService;
        this.securityService = securityService;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> logar(@RequestBody @Valid LoginUsuarioDTO usuarioDTO) {
        log.info("Tentativa de login para o usuário: {}", usuarioDTO.login());
        String token = securityService.logar(usuarioDTO);
        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioDTO> cadastrar(@RequestBody @Valid CriarUsuarioDTO usuarioDTO) {
        usuarioService.criarUsuario(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/trocar-senha")
    public ResponseEntity<Object> trocarSenha(Authentication authentication, @RequestBody @Valid TrocaSenhaUsuarioDTO usuarioDTO) {
        usuarioService.atualizarSenha(authentication.getName(), usuarioDTO);
        return ResponseEntity.ok(Map.of("message", "Senha atualizada com sucesso"));
    }
}
