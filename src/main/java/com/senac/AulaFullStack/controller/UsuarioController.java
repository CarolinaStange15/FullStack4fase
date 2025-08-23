package com.senac.AulaFullStack.controller;

import com.senac.AulaFullStack.model.Usuario;
import com.senac.AulaFullStack.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/usuarios")
@Tag(name="Controlador de usuários",description = "Camada responsável por controlar os registros")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @GetMapping("/{id}")
    @Operation(summary = "Consultar Usuário por ID")
    public ResponseEntity<Usuario> consultaPorId(@PathVariable Long id){

        var usuario = usuarioRepository.findById(id).orElse(null);

         if (usuario == null){
          return ResponseEntity.notFound().build();
      }

      return ResponseEntity.ok(usuario);
    }

    @GetMapping
    @Operation( summary = "Consultar usuários", description = "Método responsável por consultar todos os usuários")
    public ResponseEntity<?> consultarTodos(){

        return ResponseEntity.ok(usuarioRepository.findAll());
//
    }

    @PostMapping
    @Operation(summary = "Cadastrar usuário", description = "Método responsável por cadastrar usuários")
    public ResponseEntity<?> cadastrarUsuario(@RequestBody Usuario usuario){
        try{
            var usuarioResponse = usuarioRepository.save(usuario);
            return ResponseEntity.ok(usuarioResponse);
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


}
