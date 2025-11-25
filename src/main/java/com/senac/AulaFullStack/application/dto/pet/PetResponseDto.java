package com.senac.AulaFullStack.application.dto.pet;

import com.senac.AulaFullStack.domain.entity.Especie;
import com.senac.AulaFullStack.domain.entity.Pet;

public record PetResponseDto (Long id, String nome, String idadeAproximada, String descricao, Especie especieid, Pet.StatusPet status) {

    public PetResponseDto(Pet pet) {
        this(
                pet.getId(),
                pet.getNome(),
                pet.getIdadeAproximada(),
                pet.getDescricao(),
                pet.getEspecie(),
                pet.getStatus()
        );
    }

}
