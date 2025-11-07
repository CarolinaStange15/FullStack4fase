package com.senac.AulaFullStack.infra.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {


    @Autowired
    JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception{
        return http.cors(Customizer.withDefaults())
                        .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests( auth ->
                auth
                        .requestMatchers("/auth/login").permitAll()
                        .requestMatchers("/auth/registrarnovasenha").permitAll()
                        .requestMatchers("/auth/esquecisenha").permitAll()

                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() //librando o cors
                        .requestMatchers("/swagger-resources/**").permitAll()
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/v3/api-docs/**").permitAll()
                     //   .requestMatchers("/usuarios/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST , "/usuarios").permitAll()
                        .requestMatchers("/especies").hasRole("ADMIN")
                        .requestMatchers("/pets").hasRole("ADMIN")

                        .requestMatchers("/pets/**").permitAll()

                       // .requestMatchers("/pets/**/editar").permitAll()
                        //.requestMatchers("/auth/recuperarsenha/envio").hasRole("ADMIN")
                        .anyRequest().authenticated()

                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


}
