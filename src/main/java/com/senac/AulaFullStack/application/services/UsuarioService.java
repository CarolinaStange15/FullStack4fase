package com.senac.AulaFullStack.application.services;


import com.senac.AulaFullStack.application.dto.login.LoginRequestDto;
import com.senac.AulaFullStack.application.dto.login.RecuperarSenhaDto;
import com.senac.AulaFullStack.application.dto.usuario.*;
import com.senac.AulaFullStack.domain.entity.Ong;
import com.senac.AulaFullStack.domain.entity.Usuario;
import com.senac.AulaFullStack.domain.interfaces.IEnvioEmail;
import com.senac.AulaFullStack.domain.repository.OngRepository;
import com.senac.AulaFullStack.domain.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private IEnvioEmail iEnvioEmail;

    @Autowired
    private OngRepository ongRepository;

    @Autowired
    private TokenService tokenService;

    public boolean validarSenha(LoginRequestDto login){
        return usuarioRepository.existsUsuarioByEmailContainingAndSenha(login.email(), login.senha());
    }

   // @Transactional
    public UsuarioResponseDto consultarPorId(Long id){
     return usuarioRepository.findById(id)
             .map(UsuarioResponseDto::new)
             .orElse(null);
    }

    public List<UsuarioResponseDto> consultarTodosSemFiltro(){
        return usuarioRepository.findAll().stream().map(UsuarioResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public UsuarioResponseDto salvarUsuario(UsuarioRequestDto usuarioRequest) {

        Ong ong;
        if (usuarioRequest.ongId() != null){
             ong = ongRepository.findById(usuarioRequest.ongId()).orElse(null);
        } else {
            ong = null;
        }

        var usuario = usuarioRepository.findByCpf(usuarioRequest.cpf())
                .map(u -> {
            u.setNome(usuarioRequest.nome());
            u.setSenha(usuarioRequest.senha());
            u.setRole(usuarioRequest.role());
            u.setEmail(usuarioRequest.email());
            u.setTelefone(usuarioRequest.telefone());
            if(ong !=null)
                u.setOng(ong);

            return u;
        }) .orElse(new Usuario(usuarioRequest,ong));

        usuarioRepository.save(usuario);
        return usuario.toDtoResponse();

    }

    public List<UsuarioResponseDto> consultarPaginadoFiltrado(Long take, Long page, String filtro) {
        return usuarioRepository.findAll().stream()
                .sorted(Comparator.comparing(Usuario::getId).reversed())
//                .filter(p -> p.getDataCadastro().isAfter(LocalDateTime.now().plusDays(-7)))
                    .filter(a -> filtro != null ? a.getNome().contains(filtro) : true)

                .skip((long) page * take).limit(take).map(UsuarioResponseDto::new).collect(Collectors.toList());

    }

    public void recuperarSenhaEnvio(UsuarioPrincipalDto usuarioLogado) {

    iEnvioEmail.enviarEmailSimples("yumychan15@gmail.com", "BU!", "Enviei esse e-mail via sistema!");


    }

    public String gerarCodigoAleatorio(int length) {

        final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder senha = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARS.length());
            senha.append(CHARS.charAt(randomIndex));
        }
        return senha.toString();
    }

    public void recuperarSenha(RecuperarSenhaDto recuperarSenhaDto) {

        var usuario = usuarioRepository.findByEmail(recuperarSenhaDto.email()).orElse(null);

        if (usuario != null){
            var codigo = gerarCodigoAleatorio(8);

            usuario.setTokenSenha(codigo);
            usuarioRepository.save(usuario);

            iEnvioEmail.enviarEmailComTemplate(recuperarSenhaDto.email(), "Código de recuperação",codigo);


        }
    }

    public void registrarNovaSenha(RegistrarNovaSenhaDto registrarNovaSenhaDto) {
        var  usuario = usuarioRepository
                .findByEmailAndTokenSenha(
                        registrarNovaSenhaDto.email(),
                        registrarNovaSenhaDto.token()
                ).orElse(null);
        if (usuario != null) {

            usuario.setSenha(registrarNovaSenhaDto.senha());
            usuarioRepository.save(usuario);
        }
    }

    @Transactional
    public UsuarioResponseDto editarUsuario( UsuarioRequestDto usuarioRequest, UsuarioPrincipalDto usuarioPrincipalDto) {

        Usuario usuarioLogado = usuarioRepository.findById(usuarioPrincipalDto.id())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));



        // Atualiza apenas campos permitidos
        usuarioLogado.setNome(usuarioRequest.nome());
        usuarioLogado.setTelefone(usuarioRequest.telefone());
        usuarioLogado.setEmail(usuarioRequest.email());
        usuarioLogado.setSenha(usuarioRequest.senha());



        usuarioRepository.save(usuarioLogado);

        return usuarioLogado.toDtoResponse();
    }

    public UsuarioResponseDto buscarUsuarioLogado(UsuarioPrincipalDto principal) {
        var usuario = usuarioRepository.findById(principal.id())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return usuario.toDtoResponse();
    }

    @Transactional
    public UsuarioResponseDto vincularUsuarioComOng(Long ongId, Usuario usuarioLogado) {
        Ong ong = ongRepository.findById(ongId)
                .orElseThrow(() -> new RuntimeException("ONG não encontrada"));

        usuarioLogado.setOng(ong);
        usuarioRepository.save(usuarioLogado);

        return usuarioLogado.toDtoResponse();
    }




}


