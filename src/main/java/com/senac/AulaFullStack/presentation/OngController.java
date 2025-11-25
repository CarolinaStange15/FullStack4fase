package com.senac.AulaFullStack.presentation;


import com.senac.AulaFullStack.application.dto.ong.OngRequestDto;
import com.senac.AulaFullStack.application.dto.ong.OngResponseDto;
import com.senac.AulaFullStack.application.dto.usuario.UsuarioPrincipalDto;
import com.senac.AulaFullStack.application.dto.usuario.UsuarioResponseDto;
import com.senac.AulaFullStack.application.services.OngService;
import com.senac.AulaFullStack.application.services.UsuarioService;
import com.senac.AulaFullStack.domain.entity.Usuario;
import com.senac.AulaFullStack.domain.repository.OngRepository;
import com.senac.AulaFullStack.domain.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.PanelUI;
import java.util.List;

@RestController
@RequestMapping("/ongs")
@Tag(name = "Controlador de Ongs de animais", description = "Camada responsável por controlar registros")
public class OngController {
    @Autowired
    private OngRepository ongRepository;
    @Autowired
    private OngService ongService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;



    @GetMapping("/{id}")
    @Operation(summary = "Consultar uma Ong por ID",description = "Método responsável por consultar ong por um ID específico")
    public ResponseEntity<OngResponseDto> consultarPorId(@PathVariable Long id){
        var ong = ongService.consultarPorId(id);

        if (ong == null){
            return ResponseEntity.notFound().build();

        }
        return ResponseEntity.ok(ong);
    }

    @GetMapping
    @Operation(summary = "Consultar Ongs",description = "Método responsável por consultar todas as Ongs")
    public ResponseEntity<List<OngResponseDto>> consultarTodos(){

        return ResponseEntity.ok(ongService.consultarTodosSemFiltro());
    }

    @PostMapping
    @Operation(summary = "Registrar Ong", description = "Método responsável por cadastrar/editar Ong")
    public ResponseEntity<?> registrar(@RequestBody OngRequestDto ong) {
        try {
            var ongResponse = ongService.salvarOng(ong);
            return ResponseEntity.ok(ongResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Deletar Ong", description = "Método responsável por deletar Ong")
    public ResponseEntity<Void> deletar(@PathVariable Long id ) {
        ongService.deletar(id);
        return ResponseEntity.noContent().build();
    }

//    @PostMapping("/solicitarCadastro")
//    public ResponseEntity<OngResponseDto> solicitarOng(@RequestBody OngRequestDto dto,
//                                                       @AuthenticationPrincipal Usuario usuario) {
//        //Trocar aqui igual no pet
//        OngResponseDto response = ongService.solicitarOng(dto, usuario);
//        return ResponseEntity.ok(response);
//    }

    @PostMapping("/cadastrar")
    @Operation(summary = "Cadastrar Ong", description = "Método responsável por cadastrar Ong")

    public ResponseEntity<OngResponseDto> criar(@RequestBody OngRequestDto dto) {
        OngResponseDto response = ongService.criarOng(dto);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/minhaOng")
    public ResponseEntity<OngResponseDto> atualizarMinhaOng(
            @RequestBody OngRequestDto ongRequestDto,
            @AuthenticationPrincipal UsuarioPrincipalDto usuarioPrincipalDto) {

        // Busca o usuário logado
        var usuarioLogado = usuarioRepository.findById(usuarioPrincipalDto.id())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Chama o service
        var response = ongService.atualizarMinhaOng(ongRequestDto, usuarioLogado);

        return ResponseEntity.ok(response);
    }








}
