package com.senac.AulaFullStack.application.dto.ong;

import com.senac.AulaFullStack.domain.entity.Ong;

public record OngResponseDto(
        Long id, String nome, String descricao, String email,
        Ong.StatusOng status, String cnpj, String telefone, String cidade, String endereco) {

    public OngResponseDto(Ong ong) {
        this(
                ong.getId(),
                ong.getNome(),
                ong.getDescricao(),
                ong.getEmail(),
                ong.getStatus(),
                ong.getCnpj(),
                ong.getTelefone(),
                ong.getCidade(),
                ong.getEndereco()

               );
    }
}
