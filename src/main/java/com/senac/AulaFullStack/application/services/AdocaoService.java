package com.senac.AulaFullStack.application.services;


import com.senac.AulaFullStack.application.dto.adocao.AdocaoRequestDto;
import com.senac.AulaFullStack.application.dto.adocao.AdocaoResponseDto;
import com.senac.AulaFullStack.application.dto.adocao.AdocaoResumoDto;
import com.senac.AulaFullStack.application.dto.usuario.UsuarioPrincipalDto;
import com.senac.AulaFullStack.domain.entity.Adocao;
import com.senac.AulaFullStack.domain.entity.Pet;
import com.senac.AulaFullStack.domain.repository.AdocaoRepository;
import com.senac.AulaFullStack.domain.repository.PetRepository;
import com.senac.AulaFullStack.domain.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AdocaoService {

    @Autowired
    private AdocaoRepository adocaoRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;



    @Transactional
    public AdocaoResponseDto salvarPedidoAdocao(Long id, AdocaoRequestDto adocaoRequest, UsuarioPrincipalDto usuarioPrincipalDto){
         var usuarioLogado = usuarioRepository.findById(usuarioPrincipalDto.id()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet não encontrado"));


        Adocao adocao = new Adocao(adocaoRequest, usuarioLogado, pet);
        adocaoRepository.save(adocao);

        if (pet.getStatus() == Pet.StatusPet.ADOTADO) {
            throw new RuntimeException("Este pet já foi adotado.");
        }
        return adocao.toDtoResponse();


    }

    public List<AdocaoResumoDto> listarPorUsuario(UsuarioPrincipalDto usuarioPrincipalDto) {
        var usuario = usuarioRepository.findById(usuarioPrincipalDto.id())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        var adocoes = adocaoRepository.findByUsuario(usuario);

        return adocoes.stream()
                .map(AdocaoResumoDto::new)
                .toList();
    }


    public List<AdocaoResumoDto> consultarTodosSemFiltro(){
        return adocaoRepository.findAll().stream().map(AdocaoResumoDto::new).collect(Collectors.toList());
    }

    public AdocaoResumoDto consultarPorId(Long id){
        return adocaoRepository.findById(id)
                .map(AdocaoResumoDto::new)
                .orElse(null);
    }

    public List<AdocaoResumoDto> listarPorOng(Long ongId) {
        var adocoes = adocaoRepository.findByPet_Ong_Id(ongId);
        return adocoes.stream()
                .map(AdocaoResumoDto::new)
                .toList();
    }


    public List<AdocaoResumoDto> consultarTodosPendentes() {
        return adocaoRepository.findByStatus(Adocao.StatusAdocao.PENDENTE)
                .stream().map(AdocaoResumoDto::new)
                .collect(Collectors.toList());
    }

    public List<AdocaoResumoDto> consultarTodosAprovados() {
        return adocaoRepository.findByStatus(Adocao.StatusAdocao.APROVADA)
                .stream().map(AdocaoResumoDto::new)
                .collect(Collectors.toList());
    }

    public List<AdocaoResumoDto> consultarTodosReprovados() {
        return adocaoRepository.findByStatus(Adocao.StatusAdocao.REPROVADA)
                .stream().map(AdocaoResumoDto::new)
                .collect(Collectors.toList());
    }

    public List<AdocaoResumoDto> consultarPendentesPorOng(UsuarioPrincipalDto usuarioPrincipalDto) {

        var usuarioLogado = usuarioRepository.findById(usuarioPrincipalDto.id())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Long ongId = usuarioLogado.getOng().getId();

        List<Adocao> adocoes = adocaoRepository.findByPetOngIdAndStatusAndPet_Status(
                ongId,
                Adocao.StatusAdocao.PENDENTE, Pet.StatusPet.DISPONIVEL
        );

        return adocoes.stream()
                .map(AdocaoResumoDto::new)
                .collect(Collectors.toList());
    }


    public List<AdocaoResumoDto> consultarAprovadosPorOng(UsuarioPrincipalDto usuarioPrincipalDto) {

        var usuarioLogado = usuarioRepository.findById(usuarioPrincipalDto.id())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Long ongId = usuarioLogado.getOng().getId();

        List<Adocao> adocoes = adocaoRepository.findByPetOngIdAndStatus(
                ongId,
                Adocao.StatusAdocao.APROVADA);

        return adocoes.stream()
                .map(AdocaoResumoDto::new)
                .collect(Collectors.toList());
    }




    public List<AdocaoResumoDto> consultarReprovadosPorOng(UsuarioPrincipalDto usuarioPrincipalDto) {

        var usuarioLogado = usuarioRepository.findById(usuarioPrincipalDto.id())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Long ongId = usuarioLogado.getOng().getId();

        List<Adocao> adocoes = adocaoRepository.findByPetOngIdAndStatus(
                ongId,
                Adocao.StatusAdocao.REPROVADA);

        return adocoes.stream()
                .map(AdocaoResumoDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public AdocaoResponseDto aprovaAdocao(Long idAdocao) {

        Adocao adocao = adocaoRepository.findById(idAdocao)
                .orElseThrow(() -> new RuntimeException("Adoção não encontrada"));

        Pet pet = adocao.getPet();
        if (pet == null) {
            throw new RuntimeException("Adoção não possui pet vinculado");
        }

        adocao.setStatus(Adocao.StatusAdocao.APROVADA);
        pet.setStatus(Pet.StatusPet.ADOTADO);

        petRepository.save(pet);
        adocaoRepository.save(adocao);

        return adocao.toDtoResponse();
    }

    @Transactional
    public AdocaoResponseDto reprovaAdocao(Long idAdocao) {

        Adocao adocao = adocaoRepository.findById(idAdocao)
                .orElseThrow(() -> new RuntimeException("Adoção não encontrada"));

        Pet pet = adocao.getPet();
        if (pet == null) {
            throw new RuntimeException("Adoção não possui pet vinculado");
        }

        adocao.setStatus(Adocao.StatusAdocao.REPROVADA);
        pet.setStatus(Pet.StatusPet.DISPONIVEL);

        petRepository.save(pet);
        adocaoRepository.save(adocao);

        return adocao.toDtoResponse();
    }

    @Transactional
    public AdocaoResponseDto cancelaRespostaAdocao(Long idAdocao) {

        Adocao adocao = adocaoRepository.findById(idAdocao)
                .orElseThrow(() -> new RuntimeException("Adoção não encontrada"));

        Pet pet = adocao.getPet();
        if (pet == null) {
            throw new RuntimeException("Adoção não possui pet vinculado");
        }

        adocao.setStatus(Adocao.StatusAdocao.PENDENTE);
        pet.setStatus(Pet.StatusPet.DISPONIVEL);

        petRepository.save(pet);
        adocaoRepository.save(adocao);

        return adocao.toDtoResponse();
    }


}
