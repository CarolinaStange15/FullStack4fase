package com.senac.AulaFullStack.application.services;

import com.senac.AulaFullStack.application.dto.ong.OngRequestDto;
import com.senac.AulaFullStack.application.dto.ong.OngResponseDto;
import com.senac.AulaFullStack.application.dto.usuario.UsuarioPrincipalDto;
import com.senac.AulaFullStack.application.dto.usuario.UsuarioResponseDto;
import com.senac.AulaFullStack.domain.entity.Ong;
import com.senac.AulaFullStack.domain.entity.Usuario;
import com.senac.AulaFullStack.domain.interfaces.IEnvioEmail;
import com.senac.AulaFullStack.domain.repository.OngRepository;
import com.senac.AulaFullStack.domain.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OngService {

    @Autowired
    private OngRepository ongRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;





@Autowired
private IEnvioEmail  iEnvioEmail;

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


//    public OngResponseDto solicitarOng(OngRequestDto dto, Usuario usuario) {
//        Ong ong = new Ong();
//        ong.setNome(dto.nome());
//        ong.setCnpj(dto.cnpj());
//        ong.setEmail(dto.email());
//        ong.setTelefone(dto.telefone());
//        ong.setDescricao(dto.descricao());
//        ong.setUsuario(usuario);
//        ong.setStatus(Ong.StatusOng.PENDENTE);
//
//        Ong saved = ongRepository.save(ong);
//
//
//
//        // Notifica todos os admins
//        List<Usuario> admins = usuarioRepository.findByRole(usuario.getRole());
//        for (Usuario admin : admins) {
//            iEnvioEmail.enviarEmailSolicitacaoCadastroOng(
//                    admin.getEmail(),
//                    "Nova solicitação de ONG",
//                    "O usuário " + usuario.getNome() + " solicitou a criação da ONG: " + dto.nome()
//            );
//        }
//
//
//        return new OngResponseDto(saved.getId(), saved.getNome(), saved.getEmail(), saved.getStatus(), saved.getTokenCriacao());
//    }

    @Transactional
    public OngResponseDto criarOng(OngRequestDto ongRequest) {
        Ong ong = new Ong(ongRequest);
        ongRepository.save(ong);
        return ong.toDtoResponse();
    }




    @Transactional
    public OngResponseDto atualizarMinhaOng(OngRequestDto ongRequest, Usuario usuarioLogado) {

        Ong ong = usuarioLogado.getOng();

        if (ong == null) {
            throw new RuntimeException("Usuário não possui ONG vinculada.");
        }

        // Atualiza os campos
        ong.setNome(ongRequest.nome());
        ong.setDescricao(ongRequest.descricao());
        ong.setEmail(ongRequest.email());
        ong.setCnpj(ongRequest.cnpj());
        ong.setTelefone(ongRequest.telefone());
        ong.setCidade(ongRequest.cidade());
        ong.setEndereco(ongRequest.endereco());
        ong.setStatus(ongRequest.status()); // assume que pode editar status

        // JPA atualiza sozinho ao final da transação
        return ong.toDtoResponse();
    }



}
