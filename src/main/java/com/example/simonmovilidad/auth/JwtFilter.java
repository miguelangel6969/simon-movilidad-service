package com.example.simonmovilidad.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// 🧩 Marca esta clase como un componente de Spring para que sea detectada automáticamente
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {


        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        System.out.println("Método: " + req.getMethod());
        System.out.println("Authorization: " + req.getHeader("Authorization"));

        // Permitir preflight sin bloquear
        if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
            res.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(request, response); // ⬅️ Esto permite que CORS funcione correctamente
            return;
        }


        String path = request.getRequestURI();

        // ✅ Dejar pasar sin token el login
        if (path.startsWith("/api/auth/login") ||
                path.startsWith("/ws-alertas") ) {
            filterChain.doFilter(request, response);
            return;
        }

        // 🔍 Leer el header Authorization
        String header = request.getHeader("Authorization");


        // ❌ No hay token o formato inválido
        if (header == null || !header.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // ✂️ Extraer token
        String token = header.substring(7);

        // ❌ Token inválido
        if (!JwtUtil.validateToken(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // ✅ Token válido → continuar con la solicitud
        filterChain.doFilter(request, response);
    }
}