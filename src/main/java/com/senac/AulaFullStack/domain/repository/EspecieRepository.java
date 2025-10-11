package com.senac.AulaFullStack.domain.repository;

import com.senac.AulaFullStack.domain.entity.Especie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EspecieRepository extends JpaRepository<Especie, Long> {

}
