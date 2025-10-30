package com.senac.AulaFullStack.domain.repository;

import com.senac.AulaFullStack.domain.entity.Ong;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OngRepository extends JpaRepository<Ong,Long> {
}
