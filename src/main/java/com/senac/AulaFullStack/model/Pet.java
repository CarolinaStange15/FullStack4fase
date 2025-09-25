package com.senac.AulaFullStack.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String idadeAproximada;
    private  String descricao;
    private String contatoAdocao;

    @ManyToOne
    @JoinColumn(name = "especie_id", nullable = false)
    private Especie especie;


    public enum StatusPet {
        DISPONIVEL,
        ADOTADO
    }

    @Enumerated(EnumType.STRING) // salva o texto ("ATIVO", "ADOTADO"...)
    private StatusPet status;


}
