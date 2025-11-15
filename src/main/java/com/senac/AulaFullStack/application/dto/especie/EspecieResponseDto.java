package com.senac.AulaFullStack.application.dto.especie;

import com.senac.AulaFullStack.domain.entity.Especie;

public record EspecieResponseDto (Long id, String nome) {

    public EspecieResponseDto(Especie especie){
        this(
                especie.getId(),
                especie.getNome()
        );
    }
}
