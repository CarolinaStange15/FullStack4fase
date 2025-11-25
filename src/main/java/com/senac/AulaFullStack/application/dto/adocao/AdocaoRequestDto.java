package com.senac.AulaFullStack.application.dto.adocao;

import com.senac.AulaFullStack.domain.entity.Adocao;

public record AdocaoRequestDto (
        Long id,
       Long usuarioId ,
        Long petId,
        Adocao.StatusAdocao status
){
}
