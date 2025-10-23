package com.senac.AulaFullStack.presentation;

import com.senac.AulaFullStack.application.dto.pet.PetRequestDto;
import com.senac.AulaFullStack.application.dto.pet.PetResponseDto;
import com.senac.AulaFullStack.domain.entity.Pet;
import com.senac.AulaFullStack.domain.repository.EspecieRepository;
import com.senac.AulaFullStack.domain.repository.PetRepository;
import com.senac.AulaFullStack.application.services.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
@Tag(name="Controlador de Pets", description = "Camada responsável por controlar os registros")
public class PetController {
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private PetService petService;
    @Autowired
    private EspecieRepository especieRepository;



    @GetMapping("/{id}")
    @Operation(summary = "Consultar um pet por ID", description = "Método responsável por consultar pet por um ID específco")
    public ResponseEntity<PetResponseDto> consultaPorId(@PathVariable Long id){
        var pet = petService.consultarPorId(id);

        if (pet == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pet);
    }

    @GetMapping
    @Operation(summary = "Consultar pets", description = "Método responsável por consultar todos os Pets")
    public ResponseEntity<List<PetResponseDto>> consultaTodos(){

        return ResponseEntity.ok(petService.consultarTodosSemFiltro());
    }

    @PostMapping
    @Operation(summary = "Cadastrar Pet", description = "Método responsável por cadastrar pet")
    public ResponseEntity<?> cadastrarPet(@RequestBody PetRequestDto pet) {
        try {
            var petResponse = petService.salvarPet(pet);
            return ResponseEntity.ok(petResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

//    @GetMapping("/pesquisar")
//    @Operation(summary = "Pesquisar pet", description = "Pesquisar por pet pelo nome do pet e por nome do tutor")
//    public ResponseEntity<?> pesquisarPet(@RequestParam String nome, @RequestParam String nomeTutor){
//        var pet = petRepository.findByNomeAndNomeTutor(nome, nomeTutor).orElse(null);
//        if (pet == null){
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(pet);
//    }

//    @PutMapping(path = "/{id}")
//    @Operation(summary = "Atualizar Pet", description = "Método responsável por atualizar pet")
//
//    public ResponseEntity<Pet> update(@PathVariable(name = "id") Long id,
//                                      @RequestBody  PetRequestDto dto) {
//        Pet atualizado = petService.update(id, dto);
//        return ResponseEntity.ok(atualizado);
//
//    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Deletar Pet", description = "Método responsável por deletar pet")

    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
        petService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
