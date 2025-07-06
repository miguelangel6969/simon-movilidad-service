package com.example.simonmovilidad.controller;

import com.example.simonmovilidad.auth.JwtUtil;
import com.example.simonmovilidad.model.Rol;
import com.example.simonmovilidad.model.Usuario;
import com.example.simonmovilidad.repository.RolRepository;
import com.example.simonmovilidad.repository.UsuarioRepository;
import com.example.simonmovilidad.util.Mensajes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String user = credentials.get("nombre");
        String pass = credentials.get("contrasena");

        Optional<Usuario> usuarioOpt = usuarioRepository.findByNombreAndContrasena(user, pass);

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();

            Rol rol = usuario.getRol();
            if (rol == null) {
                return ResponseEntity.status(500).body(Mensajes.ROL_NO_AUTORIZADO);
            }

            String token = JwtUtil.generateToken(user, rol.getNombre());
            return ResponseEntity.ok(Map.of("token", token));
        } else {
            return ResponseEntity.status(401).body(Mensajes.CREDENCIALES_INVALIDAS);
        }
    }
}