package com.senac.AulaFullStack.application.dto.usuario;

import com.senac.AulaFullStack.domain.entity.Usuario;

public record UsuarioResponseDto(Long id, String nome, String cpf, String email) {

    public UsuarioResponseDto(Usuario usuario){
        this(
                usuario.getId(), usuario.getNome(), usuario.getCpf(), usuario.getEmail() //construtor tipado dentro de um record
        );
    }

}
