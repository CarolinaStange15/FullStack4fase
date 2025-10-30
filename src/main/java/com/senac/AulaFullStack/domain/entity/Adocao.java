package com.senac.AulaFullStack.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Adocao")
public class Adocao {




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_pet", nullable = false)
    private Pet pet;

    public enum StatusAdocao {
        PENDENTE,
        APROVADA,
        REPROVADA
    }

    @Enumerated(EnumType.STRING)
    private Adocao.StatusAdocao status;

}
