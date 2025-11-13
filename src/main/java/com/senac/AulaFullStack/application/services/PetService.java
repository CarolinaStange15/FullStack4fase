package com.senac.AulaFullStack.application.services;

import com.senac.AulaFullStack.application.dto.pet.PetRequestDto;
import com.senac.AulaFullStack.application.dto.pet.PetResponseDto;
import com.senac.AulaFullStack.application.dto.usuario.UsuarioResponseDto;
import com.senac.AulaFullStack.domain.entity.Especie;
import com.senac.AulaFullStack.domain.entity.Pet;
import com.senac.AulaFullStack.domain.entity.Usuario;
import com.senac.AulaFullStack.domain.repository.EspecieRepository;
import com.senac.AulaFullStack.domain.repository.PetRepository;
import com.senac.AulaFullStack.domain.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PetService {


    @Autowired
    private PetRepository petRepository;
    @Autowired

    private EspecieRepository especieRepository;




        public PetService(PetRepository petRepository, EspecieRepository especieRepository) {
            this.petRepository = petRepository;
            this.especieRepository = especieRepository;
        }


        public PetResponseDto consultarPorId(Long id){
            return petRepository.findById(id)
                    .map(PetResponseDto::new)
                    .orElse(null);
        }

        public List<PetResponseDto> consultarTodosSemFiltro(){
            return petRepository.findAll().stream().map(PetResponseDto::new).collect(Collectors.toList());
        }

    @Transactional
    public PetResponseDto salvarPet(PetRequestDto petRequest) {
        // busca a espécie no banco
        Especie especie = especieRepository.findById(petRequest.especieId())
                .orElseThrow(() -> new RuntimeException("Espécie não encontrada"));

        var pet = petRepository.findById(petRequest.id())
                .map(p -> {
                    p.setNome(petRequest.nome());
                    p.setIdadeAproximada(petRequest.idadeAproximada());
                    p.setDescricao(petRequest.descricao());
                    p.setContatoAdocao(petRequest.contatoAdocao());
                    p.setStatus(petRequest.status());
                    p.setEspecie(especie);
                    return p;
                })
                .orElse(new Pet(petRequest, especie));

        petRepository.save(pet);
        return pet.toDtoResponse();
    }
    public void delete(long id) {
        petRepository.deleteById(id);
    }


    //Filtrando por ONGID

    public List<PetResponseDto> consultarTodosPorOng() {
        Usuario usuarioLogado = (Usuario) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        if (usuarioLogado.getOng() == null) {
            return petRepository.findAll()
                    .stream()
                    .map(PetResponseDto::new)
                    .collect(Collectors.toList());
        }


        Long ongId = usuarioLogado.getOng().getId();
        return petRepository.findByOngId(ongId)
                .stream()
                .map(PetResponseDto::new)
                .collect(Collectors.toList());
    }



}
