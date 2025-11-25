package com.senac.AulaFullStack.application.dto.pet;

import com.senac.AulaFullStack.domain.entity.Pet;

public record PetResumoDto(Long id, String nome, String descricao, String ongNome) {

    public PetResumoDto(Pet pet){
        this(
                pet.getId(),
                pet.getNome(),
                pet.getDescricao(),
                pet.getOng().getNome()
        );
    }
}
