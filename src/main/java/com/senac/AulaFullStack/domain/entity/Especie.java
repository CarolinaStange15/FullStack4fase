package com.senac.AulaFullStack.domain.entity;
import com.senac.AulaFullStack.application.dto.especie.EspecieRequestDto;
import com.senac.AulaFullStack.application.dto.especie.EspecieResponseDto;
import com.senac.AulaFullStack.application.dto.ong.OngResponseDto;
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
        this.setNome(especieRequest.nome());
    }

    public EspecieResponseDto toDtoResponse() {
        return  new EspecieResponseDto(this);
    }
}
