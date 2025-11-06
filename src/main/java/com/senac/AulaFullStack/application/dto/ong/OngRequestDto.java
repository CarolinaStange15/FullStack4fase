package com.senac.AulaFullStack.application.dto.ong;

public record OngRequestDto (
        Long id,
        String nome,
        String descricao,
        String email,
        String cnpj,
        String telefone
){}


