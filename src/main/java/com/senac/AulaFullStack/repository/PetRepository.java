package com.senac.AulaFullStack.repository;

import com.senac.AulaFullStack.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PetRepository extends JpaRepository<Pet, Long> {
    Optional<Pet>findByNomeAndNomeTutor(String nome, String nomeTutor);
}
