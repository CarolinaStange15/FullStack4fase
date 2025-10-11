package com.senac.AulaFullStack.domain.entity;

import jakarta.persistence.*;
import lombok.*;

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

    @ManyToOne
    @JoinColumn(name="ong_id")
    private Ong ong;


    public enum StatusPet {
        DISPONIVEL,
        ADOTADO,
        ADOCAOPENDENTE
    }

    @Enumerated(EnumType.STRING) // salva o texto ("ATIVO", "ADOTADO"...)
    private StatusPet status;


}
