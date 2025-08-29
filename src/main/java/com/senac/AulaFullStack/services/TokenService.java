package com.senac.AulaFullStack.services;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
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

    public String gerarToken(String usuario, String senha){
        Algorithm algorithm = Algorithm.HMAC256(secret);

        String token = JWT.create()
                .withIssuer(emissor)
                .withSubject(usuario)//informação que vou carregar no token
                .withExpiresAt(this.gerarDataExpiracao())
                .sign(algorithm);
        return token;
    }




    private Instant gerarDataExpiracao(){
        var dataAtual = LocalDateTime.now();
        var novaData = dataAtual.plusMinutes(tempo);

        return novaData.toInstant(ZoneOffset.of("-03:00"));
    }



}
