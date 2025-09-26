package com.senac.AulaFullStack.controller;

import com.senac.AulaFullStack.dto.PetRequestDto;
import com.senac.AulaFullStack.model.Pet;
import com.senac.AulaFullStack.repository.PetRepository;
import com.senac.AulaFullStack.services.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pets")
@Tag(name="Controlador de Pets", description = "Camada responsável por controlar os registros")
public class PetController {
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private PetService petService;

    //Repository

    @GetMapping("/{id}")
    @Operation(summary = "Consultar um pet por ID", description = "Método responsável por consultar pet por um ID específco")
    public ResponseEntity<?> consultaPorId(@PathVariable Long id){
        var pet = petRepository.findById(id).orElse(null);
        if (pet == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pet);
    }

    @GetMapping
    @Operation(summary = "Consultar pets", description = "Método responsável por consultar todos os Pets")
    public ResponseEntity<?> consultaTodos(){

        return ResponseEntity.ok(petRepository.findAll());
    }

    @PostMapping
    @Operation(summary = "Cadastrar Pet", description = "Método responsável por cadastrar pet")
    public ResponseEntity<?> cadastrarPet(@RequestBody Pet pet){
        try{
            var petResponse = petRepository.save(pet);
            return ResponseEntity.ok(petResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
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

    @PutMapping(path = "/{id}")
    @Operation(summary = "Atualizar Pet", description = "Método responsável por atualizar pet")

    public ResponseEntity<Pet> update(@PathVariable(name = "id") Long id,
                                      @RequestBody  PetRequestDto pet) {
        Pet atualizado = petService.update(id, pet);
        return ResponseEntity.ok(atualizado);

    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Deletar Pet", description = "Método responsável por deletar pet")

    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
        petService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
