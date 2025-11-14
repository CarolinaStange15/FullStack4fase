package com.senac.AulaFullStack.domain.entity;
import com.senac.AulaFullStack.application.dto.especie.EspecieRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "especie")
public class Especie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    public Especie(EspecieRequestDto especieRequest) {
    }
}
