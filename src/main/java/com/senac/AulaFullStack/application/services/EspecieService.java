package com.senac.AulaFullStack.application.services;

import com.senac.AulaFullStack.application.dto.especie.EspecieRequestDto;
import com.senac.AulaFullStack.application.dto.especie.EspecieResponseDto;
import com.senac.AulaFullStack.application.dto.ong.OngRequestDto;
import com.senac.AulaFullStack.application.dto.ong.OngResponseDto;
import com.senac.AulaFullStack.domain.entity.Especie;
import com.senac.AulaFullStack.domain.entity.Ong;
import com.senac.AulaFullStack.domain.repository.EspecieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service

public class EspecieService {

    @Autowired
    private EspecieRepository especieRepository;

    public EspecieResponseDto salvarEspecie(EspecieRequestDto especieRequest) {
        Especie especie = especieRepository.findById(especieRequest.id())
                .map(e -> {
                    e.setNome(especieRequest.nome());
                    return e;
                })
                .orElse(new Especie (especieRequest));

        especieRepository.save(especie);
        return especie.toDtoResponse();
    }



}
