package com.senac.AulaFullStack.domain.repository;

import com.senac.AulaFullStack.domain.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
}
