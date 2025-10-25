package com.senac.AulaFullStack.application.services;


import com.senac.AulaFullStack.application.dto.login.LoginRequestDto;
import com.senac.AulaFullStack.application.dto.login.RecuperarSenhaDto;
import com.senac.AulaFullStack.application.dto.usuario.RegistrarNovaSenhaDto;
import com.senac.AulaFullStack.application.dto.usuario.UsuarioPrincipalDto;
import com.senac.AulaFullStack.application.dto.usuario.UsuarioRequestDto;
import com.senac.AulaFullStack.application.dto.usuario.UsuarioResponseDto;
import com.senac.AulaFullStack.domain.entity.Usuario;
import com.senac.AulaFullStack.domain.interfaces.IEnvioEmail;
import com.senac.AulaFullStack.domain.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public UsuarioResponseDto salvarUsuario(UsuarioRequestDto usuarioRequest){
        var usuario = usuarioRepository.findByCpf(usuarioRequest.cpf())
                .map(u -> {
            u.setNome(usuarioRequest.nome());
            u.setSenha(usuarioRequest.senha());
            u.setRole(usuarioRequest.role());
            u.setEmail(usuarioRequest.email());
            return u;
        }) .orElse(new Usuario(usuarioRequest));

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
}
