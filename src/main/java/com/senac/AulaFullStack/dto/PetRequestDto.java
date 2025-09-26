package com.senac.AulaFullStack.dto;

import com.senac.AulaFullStack.model.Especie;
import com.senac.AulaFullStack.model.Pet;

public record PetRequestDto(String nome,
                            String idadeAproximada,
                            String descricao,
                            String contatoAdocao,
                            Long especieId,
                            Pet.StatusPet status) {
}
