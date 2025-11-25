package com.senac.AulaFullStack.domain.entity;

import com.senac.AulaFullStack.application.dto.adocao.AdocaoRequestDto;
import com.senac.AulaFullStack.application.dto.adocao.AdocaoResponseDto;
import com.senac.AulaFullStack.application.dto.pet.PetResponseDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.parameters.P;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Adocao")
public class Adocao {

    public Adocao (AdocaoRequestDto adocaoRequest, Usuario usuario, Pet pet){

       this.setUsuario(usuario);
       this.setPet(pet);
        this.setStatus(adocaoRequest.status());

        if (this.getDataCadastro() == null) {
            this.setDataCadastro(LocalDateTime.now());
        }
        if (this.getStatus() == null){
            this.setStatus(StatusAdocao.PENDENTE);
        }
    }





    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDateTime dataCadastro;


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


    public AdocaoResponseDto toDtoResponse(){
        return new AdocaoResponseDto(this);
    }


}
