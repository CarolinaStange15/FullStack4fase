package com.senac.AulaFullStack.application.dto.usuario;

import com.senac.AulaFullStack.domain.entity.Ong;
import com.senac.AulaFullStack.domain.entity.Usuario;

public record UsuarioResponseDto(Long id, String nome, String cpf, String email, Long ongId) {

    public UsuarioResponseDto(Usuario usuario){
        this(
                usuario.getId(), usuario.getNome(), usuario.getCpf(), usuario.getEmail() ,
                usuario.getOng() != null ? usuario.getOng().getId() : null //construtor tipado dentro de um record
        );
    }

}
