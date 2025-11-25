package com.senac.AulaFullStack.application.dto.ong;

import com.senac.AulaFullStack.domain.entity.Ong;

public record OngResumoDto(Long id, String nome, String cidade, String endereco, String telefone, String email) {

    public OngResumoDto(Ong ong) {
        this(
                ong.getId(),
                ong.getNome(),
                ong.getCidade(),
                ong.getEndereco(),
                ong.getTelefone(),
                ong.getEmail()
        );
    }


}
