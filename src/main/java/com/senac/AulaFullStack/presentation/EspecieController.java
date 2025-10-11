package com.senac.AulaFullStack.presentation;


import com.senac.AulaFullStack.domain.entity.Especie;
import com.senac.AulaFullStack.domain.repository.EspecieRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/especies")
@Tag(name="Controller de Especies", description = "Camada responsável por controlar os registros")
public class EspecieController {

    @Autowired
    private EspecieRepository especieRepository;

//    @GetMapping("/{id}")
//    @Operation(summary = "Consultar espécie por ID", description = "Método responsável por consultar espécie por ID0")
//    public ResponseEntity<?> consultaPorId(@PathVariable Long id){
//        var especie = especieRepository.findById(id).orElse(null);
//        if (especie == null){
//            return ResponseEntity.ok().build();
//        }
//    return ResponseEntity.ok(especie);
//    }

    @GetMapping
    @Operation(summary = "Consultar especies", description = "Método responsável por consultar todas as espécies de animais")
    public ResponseEntity<?> consultarTodos(){
        return ResponseEntity.ok(especieRepository.findAll());
    }

    @PostMapping
    @Operation(summary = "Cadastrar Espécies", description = "Método responsável por cadastrar uma espécie de animal")
    public ResponseEntity<?> cadastrarEspecie(@RequestBody Especie especie){
        try {
            var especieResponse = especieRepository.save(especie);
            return ResponseEntity.ok(especieResponse);
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }






}
