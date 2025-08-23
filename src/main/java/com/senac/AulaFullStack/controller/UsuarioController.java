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
    public ResponseEntity<Usuario> consultaPorId(@PathVariable Long id){

        var usuario = usuarioRepository.findById(id).orElse(null);

         if (usuario == null){
          return ResponseEntity.notFound().build();
      }

      return ResponseEntity.ok(usuario);
    }

    @GetMapping
    @Operation( summary = "usuarios", description = "Método responsável por ----")
    public ResponseEntity<?> consultarTodos(){

        return ResponseEntity.ok(usuarioRepository.findAll());
//        List<Usuario> usuariosNome = List.of(new Usuario(1L,"Carol","00000000001","123455","carolina.n.stange@gmail.com"),
//                new Usuario(2L,"Tefy","00000000002","125455","Tefynha.capanhoni@gmail.com"),
//                new Usuario(3L,"Maria Laura","00000000003","1233455","Marizinha.Andrade@gmail.com")
//                );

    }

    @PostMapping
    @Operation(summary = "Salvar o usuário", description = "Método responsável por criar usuários")
    public ResponseEntity<?> salvarUsuario(@RequestBody Usuario usuario){
        try{
            var usuarioResponse = usuarioRepository.save(usuario);
            return ResponseEntity.ok(usuarioResponse);
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


}
