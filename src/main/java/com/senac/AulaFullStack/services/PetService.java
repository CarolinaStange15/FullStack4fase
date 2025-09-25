package com.senac.AulaFullStack.services;

import com.senac.AulaFullStack.dto.PetRequestDto;
import com.senac.AulaFullStack.model.Pet;
import com.senac.AulaFullStack.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class PetService {


    @Autowired
    private PetRepository petRepository;

    public Pet update(Long id, PetRequestDto petDto) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet não encontrado"));

        pet.setNome(petDto.nome());
        pet.setIdadeAproximada(petDto.idadeAproximada());
        pet.setDescricao(petDto.descricao());
        pet.setContatoAdocao(petDto.contatoAdocao());
        pet.setStatus(petDto.status());

        return petRepository.save(pet);
    }

    public void delete(long id) {
        petRepository.deleteById(id);
    }
}