package com.senac.AulaFullStack.application.services;

import com.senac.AulaFullStack.application.dto.ong.OngRequestDto;
import com.senac.AulaFullStack.application.dto.ong.OngResponseDto;
import com.senac.AulaFullStack.domain.entity.Ong;
import com.senac.AulaFullStack.domain.entity.Usuario;
import com.senac.AulaFullStack.domain.interfaces.IEnvioEmail;
import com.senac.AulaFullStack.domain.repository.OngRepository;
import com.senac.AulaFullStack.domain.repository.UsuarioRepository;
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


    public OngResponseDto solicitarOng(OngRequestDto dto, Usuario usuario) {
        Ong ong = new Ong();
        ong.setNome(dto.nome());
        ong.setCnpj(dto.cnpj());
        ong.setEmail(dto.email());
        ong.setTelefone(dto.telefone());
        ong.setDescricao(dto.descricao());
        ong.setUsuario(usuario);
        ong.setStatus(Ong.StatusOng.PENDENTE);

        Ong saved = ongRepository.save(ong);



        // Notifica todos os admins
        List<Usuario> admins = usuarioRepository.findByRole(usuario.getRole());
        for (Usuario admin : admins) {
            iEnvioEmail.enviarEmailSolicitacaoCadastroOng(
                    admin.getEmail(),
                    "Nova solicitação de ONG",
                    "O usuário " + usuario.getNome() + " solicitou a criação da ONG: " + dto.nome()
            );
        }


        return new OngResponseDto(saved.getId(), saved.getNome(), saved.getEmail(), saved.getStatus(), saved.getTokenCriacao());
    }
}
