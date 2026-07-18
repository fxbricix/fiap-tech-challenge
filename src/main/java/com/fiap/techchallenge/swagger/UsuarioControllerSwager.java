package com.fiap.techchallenge.swagger;

import com.fiap.techchallenge.dto.ResponseDTO;
import com.fiap.techchallenge.dto.UsuarioDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Usuário", description = "API para gerenciamento de usuários")
public interface UsuarioControllerSwager {
    ResponseEntity<List<UsuarioDTO>> buscaUsuarioPorNome(@RequestParam @NotBlank String nome);

    ResponseEntity<UsuarioDTO> atualizarInformacoes(Authentication authentication, @RequestBody UsuarioDTO usuarioDTO);
}
