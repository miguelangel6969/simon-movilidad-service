package com.example.simonmovilidad;

import com.example.simonmovilidad.auth.JwtUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    @Test
    void testGenerateAndValidateToken() {
        String token = JwtUtil.generateToken("miguel", "ADMIN");
        assertNotNull(token);

        boolean esValido = JwtUtil.validateToken(token);
        assertTrue(esValido);

        String rol = JwtUtil.extraerRol(token);
        assertEquals("ADMIN", rol);
    }

    @Test
    void testTokenInvalido() {
        String tokenFalso = "xxx.yyy.zzz";
        assertFalse(JwtUtil.validateToken(tokenFalso));
    }
}
