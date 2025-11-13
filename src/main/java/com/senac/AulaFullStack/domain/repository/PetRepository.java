package com.senac.AulaFullStack.domain.repository;

import com.senac.AulaFullStack.domain.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PetRepository extends JpaRepository<Pet, Long> {
    Optional<Pet> findByOngId(Long OngId);


}
