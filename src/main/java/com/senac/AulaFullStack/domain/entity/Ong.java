package com.senac.AulaFullStack.domain.entity;

import com.senac.AulaFullStack.application.dto.ong.OngRequestDto;
import com.senac.AulaFullStack.application.dto.ong.OngResponseDto;
import com.senac.AulaFullStack.application.dto.ong.OngResumoDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "ong")
public class Ong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private String email;
    private String cnpj;
    private String telefone;
    private String cidade;
    private String endereco;
    private LocalDateTime dataCadastro;
    private String tokenCriacao;

    public Ong(OngRequestDto ongRequest) {
        this.setNome(ongRequest.nome());
        this.setDescricao(ongRequest.descricao());
        this.setEmail(ongRequest.email());
        this.setCnpj(ongRequest.cnpj());
        this.setTelefone(ongRequest.telefone());
        this.setStatus(ongRequest.status());
        this.setEndereco(ongRequest.endereco());
        this.setCidade(ongRequest.cidade());
        if (this.getDataCadastro() == null){
            this.setDataCadastro(LocalDateTime.now());
        }

        if (this.getStatus()== null){
            this.setStatus(StatusOng.PENDENTE);
        }
    }
    public enum StatusOng {
        PENDENTE,
        APROVADA,
        REPROVADA
    }


    @ManyToOne
    @JoinColumn(name = "usuario_id_origem")
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private StatusOng status;



    public OngResponseDto toDtoResponse() {
        return new OngResponseDto(this);
    }

    public OngResumoDto toDtoResponseResumo(){
        return new OngResumoDto(this);
    }
//    @ManyToOne
//    @JoinColumn(name = "endereco_id")
//    private Endereco endereco;




}
