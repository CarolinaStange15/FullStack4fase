package com.senac.AulaFullStack.application.services;

import com.senac.AulaFullStack.application.dto.ong.OngRequestDto;
import com.senac.AulaFullStack.application.dto.ong.OngResponseDto;
import com.senac.AulaFullStack.domain.entity.Ong;
import com.senac.AulaFullStack.domain.repository.OngRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OngService {

    @Autowired
    private OngRepository ongRepository;



    public OngResponseDto consultarPorId(Long id) {
        return ongRepository.findById(id)
                .map(OngResponseDto::new)
                .orElse(null);

    }

    public List<OngResponseDto> consultarTodosSemFiltro() {
        return ongRepository.findAll().stream().map(OngResponseDto::new).collect(Collectors.toList());
    }

    public OngResponseDto salvarOng(OngRequestDto ongRequest) {
        Ong ong = ongRepository.findById(ongRequest.id())
                .map(o -> {
                    o.setNome(ongRequest.nome());
                    o.setDescricao(ongRequest.descricao());
                    return o;
                })
                .orElse(new Ong (ongRequest));

        ongRepository.save(ong);
        return ong.toDtoResponse();
    }

    public void deletar(Long id) {
        ongRepository.deleteById(id);

    }
}
