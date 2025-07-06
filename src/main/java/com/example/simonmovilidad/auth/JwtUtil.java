package com.example.simonmovilidad.auth;

import java.util.Base64;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class JwtUtil {

    // 🔐 Clave secreta para firmar el token (debería almacenarse en una variable de entorno en producción)
    private static final String SECRET = "BCHBJHBHJZiucn21uchy8a98YHF9QWcnqw98h238a9YHf9QWcnqw98h23";

    /**
     * 🔧 Genera un token JWT con el username y el rol, válido por 1 hora
     */
    public static String generateToken(String username, String role) {
        long now = System.currentTimeMillis();              // Tiempo actual en milisegundos
        long exp = now + 1000 * 60 * 60;                    // Expiración: +1 hora

        // 🧾 Encabezado del JWT codificado en Base64URL
        String header = Base64.getUrlEncoder().withoutPadding()
                .encodeToString("{\"alg\":\"HS256\",\"typ\":\"JWT\"}".getBytes());

        // 📦 Cuerpo (payload) del token con username, rol y expiración
        String payload = Base64.getUrlEncoder().withoutPadding()
                .encodeToString(("{\"sub\":\"" + username + "\",\"role\":\"" + role + "\",\"exp\":" + exp + "}").getBytes());

        // ✍️ Firma el token con HMAC-SHA256 usando el secreto
        String signature = hmacSha256(header + "." + payload, SECRET);

        // 🔗 Retorna el token completo con formato: header.payload.signature
        return header + "." + payload + "." + signature;
    }

    /**
     * ✅ Valida un token JWT verificando que la firma sea correcta
     */
    public static boolean validateToken(String token) {
        try {
            String[] parts = token.split("\\.");                            // Separa las 3 partes del token
            String signatureCheck = hmacSha256(parts[0] + "." + parts[1], SECRET); // Recalcula la firma
            return signatureCheck.equals(parts[2]);                         // Compara la firma esperada con la recibida
        } catch (Exception e) {
            return false;  // Si hay error, el token no es válido
        }
    }

    /**
     * 🔐 Firma con algoritmo HMAC-SHA256 y clave secreta
     */
    private static String hmacSha256(String data, String secret) {
        try {
            Mac hmac = Mac.getInstance("HmacSHA256");
            hmac.init(new SecretKeySpec(secret.getBytes(), "HmacSHA256"));
            return Base64.getUrlEncoder().withoutPadding()
                    .encodeToString(hmac.doFinal(data.getBytes())); // Devuelve firma codificada en Base64URL
        } catch (Exception e) {
            throw new RuntimeException("Error en firma JWT", e); // Si falla algo, lanza error
        }
    }

    /**
     * 🔍 Extrae el valor del campo "role" desde el token JWT
     */
    public static String extraerRol(String token) {
        try {
            String[] partes = token.split("\\."); // Separa el token
            String payload = new String(Base64.getUrlDecoder().decode(partes[1])); // Decodifica el payload
            return payload.split("\"role\":\"")[1].split("\"")[0]; // Extrae el rol entre comillas
        } catch (Exception e) {
            return ""; // Si algo sale mal, retorna cadena vacía
        }
    }
}

