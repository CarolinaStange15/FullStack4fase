package com.senac.AulaFullStack.services;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.senac.AulaFullStack.model.Token;
import com.senac.AulaFullStack.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Service
public class TokenService {

    @Value("${spring.secretkey}")
    private String secret;

    @Value("${spring.tempo_expiracao}")
    private Long tempo;


    private String emissor = "Topets";

    @Autowired
    TokenRepository tokenRepository;


    public String gerarToken(String usuario, String senha){
        Algorithm algorithm = Algorithm.HMAC256(secret);

        String token = JWT.create()
                .withIssuer(emissor)
                .withSubject(usuario)//informação que vou carregar no token
                .withExpiresAt(this.gerarDataExpiracao())
                .sign(algorithm);
        tokenRepository.save(new Token(null, token, usuario));
        return token;
    }

    public String validarToken(String token){
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(emissor)
                .build();
        verifier.verify(token);
        var tokenResult = tokenRepository.findByToken(token).orElse(null);

        if (tokenResult == null){
            throw new IllegalArgumentException("Token inválido");
        }

        return tokenResult.getUsuario();


    }




    private Instant gerarDataExpiracao(){
        var dataAtual = LocalDateTime.now();
        var novaData = dataAtual.plusMinutes(tempo);

        return novaData.toInstant(ZoneOffset.of("-03:00"));
    }



}
