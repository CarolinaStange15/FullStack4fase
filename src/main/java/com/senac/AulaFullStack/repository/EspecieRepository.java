package com.senac.AulaFullStack.repository;

import com.senac.AulaFullStack.model.Especie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EspecieRepository extends JpaRepository<Especie, Long> {

}
