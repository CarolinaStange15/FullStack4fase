package com.senac.AulaFullStack.presentation;

import com.senac.AulaFullStack.application.dto.usuario.UsuarioPrincipalDto;
import com.senac.AulaFullStack.application.dto.usuario.UsuarioRequestDto;
import com.senac.AulaFullStack.application.dto.usuario.UsuarioResponseDto;
import com.senac.AulaFullStack.application.dto.usuario.UsuarioResumoDto;
import com.senac.AulaFullStack.application.services.UsuarioService;
import com.senac.AulaFullStack.domain.entity.Usuario;
import com.senac.AulaFullStack.domain.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/usuarios")
@Tag(name="Controlador de usuários",description = "Camada responsável por controlar os registros")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @GetMapping("/{id}")
    @Operation(summary = "Consultar Usuário por ID")
    public ResponseEntity<UsuarioResponseDto> consultaPorId(@PathVariable Long id){

        var usuario = usuarioService.consultarPorId(id);

    //    SecurityContextHolder.getContext().getAuthetication().getPrincipal;

         if (usuario == null){
          return ResponseEntity.notFound().build();
      }

      return ResponseEntity.ok(usuario);
    }

    @GetMapping
    @Operation( summary = "Consultar usuários", description = "Método responsável por consultar todos os usuários")
    public ResponseEntity<List<UsuarioResponseDto>> consultarTodos(){

        return ResponseEntity.ok(usuarioService.consultarTodosSemFiltro());
    }

    @PostMapping
    @Operation(summary = "Cadastrar usuário", description = "Método responsável por cadastrar usuários")
    public ResponseEntity<?> cadastrarUsuario(@RequestBody UsuarioRequestDto usuario){
        try{
            var usuarioResponse = usuarioService.salvarUsuario(usuario);
            return ResponseEntity.ok(usuarioResponse);
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/grid")
    @Operation(summary = "Usuários Grid filtrada", description = "aaaaaaaaaaaaaaaa")
    public ResponseEntity<List<UsuarioResponseDto>> consultarPaginadoFiltrado(
            @Parameter(description = "Parametro de quantidade de registro por página")
            @RequestParam long take,
            @Parameter(description = "Parametro de quantidade de páginas")
            @RequestParam Long page,
            @Parameter(description = "Parametro de filtro") @RequestParam(required = false) String filtro){
        return ResponseEntity.ok(usuarioService.consultarPaginadoFiltrado(take,page,filtro));
    }

    @PutMapping("/editar")
    @Operation(summary = "Editar usuário logado")
    public ResponseEntity<?> editarUser(
            @RequestBody UsuarioRequestDto usuarioRequest,
            @AuthenticationPrincipal UsuarioPrincipalDto usuarioPrincipalDto) {

        try {
            UsuarioResponseDto usuarioResponse =
                    usuarioService.editarUsuario(usuarioRequest, usuarioPrincipalDto);

            return ResponseEntity.ok(usuarioResponse);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/me")
    public ResponseEntity<UsuarioResponseDto> buscarUsuarioLogado(
            @AuthenticationPrincipal UsuarioPrincipalDto usuarioPrincipalDto) {

        var usuario = usuarioService.buscarUsuarioLogado(usuarioPrincipalDto);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/vincularOng")
    public ResponseEntity<UsuarioResponseDto> vincularOng(
            @RequestParam Long ongId,
            @AuthenticationPrincipal UsuarioPrincipalDto usuarioPrincipalDto) {

        var usuarioLogado = usuarioRepository.findById(usuarioPrincipalDto.id())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        UsuarioResponseDto response = usuarioService.vincularUsuarioComOng(ongId, usuarioLogado);
        return ResponseEntity.ok(response);
    }


}





