package com.senac.AulaFullStack.application.dto.ong;

import com.senac.AulaFullStack.domain.entity.Ong;

public record OngRequestDto (
        Long id,
        String nome,
        String descricao,
        String email,
        String cnpj,
        String telefone,
        String endereco,
        String cidade,
        Ong.StatusOng status

){}


