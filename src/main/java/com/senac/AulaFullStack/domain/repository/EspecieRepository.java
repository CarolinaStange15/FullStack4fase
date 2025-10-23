package com.senac.AulaFullStack.domain.repository;

import com.senac.AulaFullStack.domain.entity.Especie;
import com.senac.AulaFullStack.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EspecieRepository extends JpaRepository<Especie, Long> {



}
