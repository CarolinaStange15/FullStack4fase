package com.senac.AulaFullStack.application.dto.ong;

import com.senac.AulaFullStack.domain.entity.Ong;

public record OngResponseDto(
        Long id,
        String nome,
        String descricao,
        Ong.StatusOng status,
        String tokenCriacao) {

    public OngResponseDto(Ong ong) {
        this(
                ong.getId(),
                ong.getNome(),
                ong.getDescricao(),
                ong.getStatus(), ong.getTokenCriacao());
    }
}
