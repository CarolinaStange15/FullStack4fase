package com.senac.AulaFullStack.application.dto.adocao;

import com.senac.AulaFullStack.application.dto.pet.PetResumoDto;
import com.senac.AulaFullStack.application.dto.usuario.UsuarioResumoDto;
import com.senac.AulaFullStack.domain.entity.Adocao;

import java.time.LocalDateTime;

public record AdocaoResumoDto (Long id, LocalDateTime dataCadastro, Adocao.StatusAdocao status, UsuarioResumoDto usuarioResumo, PetResumoDto petResumo) {
    public AdocaoResumoDto(Adocao adocao){
        this(
                adocao.getId(),
                adocao.getDataCadastro(),
                adocao.getStatus(),
                new UsuarioResumoDto( adocao.getUsuario()),
                new PetResumoDto(adocao.getPet())
        );
    }

}
