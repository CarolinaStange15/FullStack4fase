package com.senac.AulaFullStack.domain.entity;

import com.senac.AulaFullStack.application.dto.pet.PetRequestDto;
import com.senac.AulaFullStack.application.dto.pet.PetResponseDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "pet")
public class Pet {


    public Pet (PetRequestDto petRequest, Especie especie){
        this.setNome(petRequest.nome());
        this.setDescricao(petRequest.descricao());
        this.setEspecie(especie);
        this.setContatoAdocao(petRequest.contatoAdocao());
        this.setIdadeAproximada(petRequest.idadeAproximada());
        this.setStatus(petRequest.status());
        if (this.getDataCadastro() == null) {
            this.setDataCadastro(LocalDateTime.now());
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String idadeAproximada;
    private  String descricao;
    private String contatoAdocao;
    private LocalDateTime dataCadastro;

    @ManyToOne
    @JoinColumn(name = "especie_id", nullable = false)
    private Especie especie;

//    @ManyToOne
//    @JoinColumn(name="ong_id")
//    private Ong ong;


    public enum StatusPet {
        DISPONIVEL,
        ADOTADO,
        ADOCAOPENDENTE
    }

    @Enumerated(EnumType.STRING) // salva o texto ("ATIVO", "ADOTADO"...)
    private StatusPet status;


    public PetResponseDto toDtoResponse(){
        return new PetResponseDto(this);
    }

}
