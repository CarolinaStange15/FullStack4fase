package com.senac.AulaFullStack.domain.entity;

import com.senac.AulaFullStack.application.dto.usuario.UsuarioRequestDto;
import com.senac.AulaFullStack.application.dto.usuario.UsuarioResponseDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "usuarios")
public class Usuario implements UserDetails {

public Usuario(UsuarioRequestDto usuarioRequest){
    this.setCpf(usuarioRequest.cpf());
    this.setNome(usuarioRequest.nome());
    this.setEmail(usuarioRequest.email());
    this.setSenha(usuarioRequest.senha());
    this.setRole(usuarioRequest.role());
    if (this.getDataCadastro() == null){
        this.setDataCadastro(LocalDateTime.now());
    }
}


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String senha;
    private String email;
    private LocalDateTime dataCadastro;
private String tokenSenha;
    private String role;

    @ManyToOne
    @JoinColumn(name="ong_id")
    private Ong ong;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if ("ADMIN".equals(this.role)) {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_USER"),
            new SimpleGrantedAuthority("ROLE_ADMIN"),
            new SimpleGrantedAuthority("ROLE_ADMINONG"));
        } else if ("ADMINONG".equals(this.role)) {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMINONG"),
            new SimpleGrantedAuthority("ROLE_USER"));

            } else {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }

    }
    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    public UsuarioResponseDto toDtoResponse() {
        return new UsuarioResponseDto(this);
    }
}
