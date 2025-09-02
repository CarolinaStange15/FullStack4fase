package com.senac.AulaFullStack.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Date dataNascimento;
    private int idadeAproximada;

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
