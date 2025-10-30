package com.senac.AulaFullStack.domain.entity;

import com.senac.AulaFullStack.application.dto.ong.OngRequestDto;
import com.senac.AulaFullStack.application.dto.ong.OngResponseDto;
import jakarta.persistence.*;
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
    private LocalDateTime dataCadastro;

    public Ong(OngRequestDto ongRequest) {
        this.setNome(ongRequest.nome());
        this.setDescricao(ongRequest.descricao());
        if (this.getDataCadastro() == null){
            this.setDataCadastro(LocalDateTime.now());
        }

    }

    public OngResponseDto toDtoResponse() {
        return new OngResponseDto(this);
    }

//    @ManyToOne
//    @JoinColumn(name = "endereco_id")
//    private Endereco endereco;




}
