package com.senac.AulaFullStack.application.dto;

import com.senac.AulaFullStack.domain.entity.Pet;

public record PetRequestDto(String nome,
                            String idadeAproximada,
                            String descricao,
                            String contatoAdocao,
                            Long especieId,
                            Pet.StatusPet status) {
}
