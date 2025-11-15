package com.senac.AulaFullStack.application.services;

import com.senac.AulaFullStack.application.dto.pet.PetRequestDto;
import com.senac.AulaFullStack.application.dto.pet.PetResponseDto;
import com.senac.AulaFullStack.application.dto.usuario.UsuarioPrincipalDto;
import com.senac.AulaFullStack.application.dto.usuario.UsuarioResponseDto;
import com.senac.AulaFullStack.domain.entity.Especie;
import com.senac.AulaFullStack.domain.entity.Ong;
import com.senac.AulaFullStack.domain.entity.Pet;
import com.senac.AulaFullStack.domain.entity.Usuario;
import com.senac.AulaFullStack.domain.repository.EspecieRepository;
import com.senac.AulaFullStack.domain.repository.OngRepository;
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
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private OngRepository ongRepository;





        public PetService(PetRepository petRepository, EspecieRepository especieRepository, OngRepository ongRepository) {
            this.petRepository = petRepository;
            this.especieRepository = especieRepository;
            this.ongRepository = ongRepository;
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
    public PetResponseDto salvarPet(PetRequestDto petRequest, UsuarioPrincipalDto usuarioPrincipalDto) {

        // 1. Buscar espécie
        Especie especie = especieRepository.findById(petRequest.especieId())
                .orElseThrow(() -> new RuntimeException("Espécie não encontrada"));

        // 2. Buscar usuário logado
        var usuarioLogado = usuarioRepository.findById(usuarioPrincipalDto.id())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // 3. SE NÃO TEM ID → CRIA PET NOVO
        if (petRequest.id() == null) {

            Pet pet = new Pet(petRequest, especie, usuarioLogado.getOng());
            petRepository.save(pet);

            return pet.toDtoResponse();
        }

        // 4. SE TEM ID → UPDATE
        Pet pet = petRepository.findById(petRequest.id())
                .orElseThrow(() -> new RuntimeException("Pet não encontrado"));

        pet.setNome(petRequest.nome());
        pet.setIdadeAproximada(petRequest.idadeAproximada());
        pet.setDescricao(petRequest.descricao());
        pet.setContatoAdocao(petRequest.contatoAdocao());
        pet.setStatus(petRequest.status());
        pet.setEspecie(especie);
        pet.setOng(usuarioLogado.getOng());

        petRepository.save(pet);
        return pet.toDtoResponse();
    }

    public void delete(long id) {
        petRepository.deleteById(id);
    }


    //Filtrando por ONGID

    public List<PetResponseDto> consultarTodosPorOng(UsuarioPrincipalDto usuarioPrincipalDto) {
        var usuarioLogado = usuarioRepository.findById(usuarioPrincipalDto.id()).orElse(null);

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
