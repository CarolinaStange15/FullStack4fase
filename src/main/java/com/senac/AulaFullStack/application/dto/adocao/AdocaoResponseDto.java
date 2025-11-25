package com.senac.AulaFullStack.application.dto.adocao;

import com.senac.AulaFullStack.application.dto.ong.OngResumoDto;
import com.senac.AulaFullStack.application.dto.pet.PetResponseDto;
import com.senac.AulaFullStack.application.dto.usuario.UsuarioResponseDto;
import com.senac.AulaFullStack.application.dto.usuario.UsuarioResumoDto;
import com.senac.AulaFullStack.domain.entity.Adocao;
import com.senac.AulaFullStack.domain.entity.Pet;
import com.senac.AulaFullStack.domain.entity.Usuario;

public record AdocaoResponseDto(Long id, UsuarioResumoDto usuarioId, PetResponseDto petId, Adocao.StatusAdocao status) {

    public AdocaoResponseDto(Adocao adocao){
        this(
                adocao.getId(),
                new UsuarioResumoDto( adocao.getUsuario()
                ),
                new PetResponseDto( adocao.getPet()),
                adocao.getStatus()

        );
    }

}
