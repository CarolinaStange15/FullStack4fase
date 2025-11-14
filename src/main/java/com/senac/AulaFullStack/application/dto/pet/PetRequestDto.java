package com.senac.AulaFullStack.application.dto.pet;

import com.senac.AulaFullStack.domain.entity.Pet;

public record PetRequestDto(
        Long id,
        String nome,
                            String idadeAproximada,
                            String descricao,
                            String contatoAdocao,
                            Long especieId,
                            Pet.StatusPet status
        ) {
}
