package com.senac.AulaFullStack.application.dto.pet;

import com.senac.AulaFullStack.application.dto.ong.OngResumoDto;
import com.senac.AulaFullStack.domain.entity.Especie;
import com.senac.AulaFullStack.domain.entity.Ong;
import com.senac.AulaFullStack.domain.entity.Pet;

public record PetAndOngResponseDto(Long id, String nome, String idadeAproximada, String descricao, Especie especieid, OngResumoDto ong) {
    public PetAndOngResponseDto(Pet pet) {
        this(
                pet.getId(),
                pet.getNome(),
                pet.getIdadeAproximada(),
                pet.getDescricao(),
                pet.getEspecie(),
                new OngResumoDto( pet.getOng()

                )

        );
    }

}
