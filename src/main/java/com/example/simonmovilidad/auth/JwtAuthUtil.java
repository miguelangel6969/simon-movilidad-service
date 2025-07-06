package com.example.simonmovilidad.auth;

import jakarta.servlet.http.HttpServletRequest;

/**
 * 🔐 Utilidad para validar autorización basada en JWT manualmente
 */
public class JwtAuthUtil {

    /**
     * ✅ Verifica si el usuario autenticado tiene el rol ADMIN
     *
     * @param request HttpServletRequest que contiene el header "Authorization"
     * @return true si el token es válido y el rol es ADMIN, false en caso contrario
     */
    public static boolean esAdmin(HttpServletRequest request) {
        // Extrae el header Authorization
        String header = request.getHeader("Authorization");

        // Si el header no existe o no comienza con "Bearer ", no es válido
        if (header == null || !header.startsWith("Bearer ")) return false;

        // Extrae el token eliminando el prefijo "Bearer "
        String token = header.substring(7);

        // Extrae el rol del token y compara si es "ADMIN"
        return "ADMIN".equalsIgnoreCase(JwtUtil.extraerRol(token));
    }
}