package com.senac.AulaFullStack.config;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI(); //cara que retorna os métodos
        if (path.equals("/auth/login")
                || path.startsWith("/swagger-resources")
                || path.startsWith("/swagger-v3/api/docs")
                || path.startsWith("/webjars")
        ) {
            filterChain.doFilter(request, response);
            return;
        }
        String header = request.getHeader("Authorization"); //qual o padrão de autenticação?
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.replace("Bearer ", "");
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Ta loko meu? ta invadindo");
            return;
        }
    }
}
