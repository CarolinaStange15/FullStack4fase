package com.senac.AulaFullStack.repository;

import com.senac.AulaFullStack.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
}
