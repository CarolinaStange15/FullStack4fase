package com.senac.AulaFullStack.presentation;


import com.senac.AulaFullStack.application.dto.adocao.AdocaoRequestDto;
import com.senac.AulaFullStack.application.dto.adocao.AdocaoResponseDto;
import com.senac.AulaFullStack.application.dto.adocao.AdocaoResumoDto;
import com.senac.AulaFullStack.application.dto.pet.PetResponseDto;
import com.senac.AulaFullStack.application.dto.usuario.UsuarioPrincipalDto;
import com.senac.AulaFullStack.application.services.AdocaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adocoes")
@Tag(name = "Adoções", description = "Controlador responsável pelas solicitações de adoção")
public class AdocaoController {

    @Autowired
    private AdocaoService adocaoService;


    @PostMapping("/{idPet}")
    @Operation(summary = "Criar pedido de adoção", description = "Gera um novo pedido de adoção para um pet específico")
    public ResponseEntity<?> criarPedidoAdocao(@PathVariable Long idPet, @RequestBody AdocaoRequestDto adocaoRequest, @AuthenticationPrincipal UsuarioPrincipalDto usuarioPrincipal) {

        try {
            AdocaoResponseDto response = adocaoService.salvarPedidoAdocao(idPet, adocaoRequest, usuarioPrincipal);
            return ResponseEntity.ok(response);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }
    @GetMapping(path = "/{id}")
    @Operation(summary = "Consultar um pedido de adoção por ID", description = "Método responsável por consultar pedido de adoção por um ID específico")
    public ResponseEntity<AdocaoResumoDto> consultaPorId(@PathVariable Long id){
        var adocao = adocaoService.consultarPorId(id);

        if (adocao == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(adocao);
    }
    @GetMapping
    @Operation(summary = "Consultar pedidos de adoção", description = "Método responsável por buscar todos os pedidos de adoção")
    public ResponseEntity<List<AdocaoResumoDto>> consultaTodos(){

        return ResponseEntity.ok(adocaoService.consultarTodosSemFiltro());
    }

    @GetMapping("/meus-pedidos")
    public ResponseEntity<?> listarPedidosDoUsuario(
            @AuthenticationPrincipal UsuarioPrincipalDto usuario
    ) {
        var pedidos = adocaoService.listarPorUsuario(usuario);
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/aprovadas")
    @Operation(summary = "Consultar adoções aprovadas", description = "Retorna todas as adoções aprovadas para a ONG do usuário logado"
    )
    public ResponseEntity<List<AdocaoResumoDto>> consultarAprovadasPorOng(@AuthenticationPrincipal UsuarioPrincipalDto usuarioPrincipalDto
    ) {
        return ResponseEntity.ok(adocaoService.consultarAprovadosPorOng(usuarioPrincipalDto));
    }

    @GetMapping("/pendentes")
    @Operation(summary = "Consultar adoções pendentes", description = "Retorna todas as adoções pendentes para a ONG do usuário logado"
    )
    public ResponseEntity<List<AdocaoResumoDto>> consultarPendentesPorOng(@AuthenticationPrincipal UsuarioPrincipalDto usuarioPrincipalDto
    ) {
        return ResponseEntity.ok(adocaoService.consultarPendentesPorOng(usuarioPrincipalDto));
    }

    @GetMapping("/reprovadas")
    @Operation(summary = "Consultar adoções reprovadas", description = "Retorna todas as adoções reprovadas para a ONG do usuário logado"
    )
    public ResponseEntity<List<AdocaoResumoDto>> consultarReprovadasPorOng(@AuthenticationPrincipal UsuarioPrincipalDto usuarioPrincipalDto
    ) {
        return ResponseEntity.ok(adocaoService.consultarReprovadosPorOng(usuarioPrincipalDto));
    }

    @PutMapping("/{id}/aprovar")
    public ResponseEntity<AdocaoResponseDto> aprovarAdocao(@PathVariable Long id) {
        AdocaoResponseDto response = adocaoService.aprovaAdocao(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/reprovar")
    public ResponseEntity<AdocaoResponseDto> reprovarAdocao(@PathVariable Long id) {
        AdocaoResponseDto response = adocaoService.reprovaAdocao(id);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/{id}/cancelarResposta")
    public ResponseEntity<AdocaoResponseDto> cancelaResposta(@PathVariable Long id) {
        AdocaoResponseDto response = adocaoService.cancelaRespostaAdocao(id);
        return ResponseEntity.ok(response);
    }
}








