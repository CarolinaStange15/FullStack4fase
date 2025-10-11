package com.senac.AulaFullStack.application.services;

import com.senac.AulaFullStack.application.dto.PetRequestDto;
import com.senac.AulaFullStack.domain.entity.Especie;
import com.senac.AulaFullStack.domain.entity.Pet;
import com.senac.AulaFullStack.domain.repository.EspecieRepository;
import com.senac.AulaFullStack.domain.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

        public Pet cadastrarPet(PetRequestDto dto) {
            Especie especie = especieRepository.findById(dto.especieId())
                    .orElseThrow(() -> new RuntimeException("Espécie não encontrada"));

            Pet pet = new Pet();
            pet.setNome(dto.nome());
            pet.setIdadeAproximada(dto.idadeAproximada());
            pet.setDescricao(dto.descricao());
            pet.setContatoAdocao(dto.contatoAdocao());
            pet.setStatus(dto.status());
            pet.setEspecie(especie);

            return petRepository.save(pet);
        }



    public Pet update(Long id, PetRequestDto petDto) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet não encontrado"));

        pet.setNome(petDto.nome());
        pet.setIdadeAproximada(petDto.idadeAproximada());
        pet.setDescricao(petDto.descricao());
        pet.setContatoAdocao(petDto.contatoAdocao());
        pet.setStatus(petDto.status());

        return petRepository.save(pet);
    }

    public void delete(long id) {
        petRepository.deleteById(id);
    }
}