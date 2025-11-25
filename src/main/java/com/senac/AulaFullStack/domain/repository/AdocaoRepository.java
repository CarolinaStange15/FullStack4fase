package com.senac.AulaFullStack.domain.repository;

import com.senac.AulaFullStack.domain.entity.Adocao;
import com.senac.AulaFullStack.domain.entity.Pet;
import com.senac.AulaFullStack.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdocaoRepository extends JpaRepository <Adocao, Long> {
    List<Adocao> findByPet_Ong_Id(Long ongId);
    List<Adocao> findByStatus(Adocao.StatusAdocao status);
    List<Adocao> findByPetOngIdAndStatus(Long ongId, Adocao.StatusAdocao status);

    List<Adocao> findByPetOngIdAndStatusAndPet_Status(Long ongId, Adocao.StatusAdocao status, Pet.StatusPet statusPet);

    List<Adocao> findByUsuario(Usuario usuario);
}
