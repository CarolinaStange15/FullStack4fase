package com.senac.AulaFullStack.controller;


import com.senac.AulaFullStack.dto.LoginRequestDto;
import com.senac.AulaFullStack.services.TokenService;
import com.senac.AulaFullStack.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name= "Controladora de autenticação", description = "Controlar a autenticação de usuários")
public class AuthController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    @Operation(summary = "Login", description = "Método responsável por efetuar o login de usuário")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto request){

        if (!usuarioService.validarSenha(request)){
            return ResponseEntity.badRequest().body("Usuário ou senha inválida!");
        }

        var token =tokenService.gerarToken(request);
        return ResponseEntity.ok(token);
    }
}
