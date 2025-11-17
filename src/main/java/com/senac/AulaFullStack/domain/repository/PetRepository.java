package com.senac.AulaFullStack.domain.repository;

import com.senac.AulaFullStack.domain.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findByOngId(Long OngId);
    List<Pet> findByStatusNot(Pet.StatusPet status);
    List<Pet> findByStatus(Pet.StatusPet status);


}
