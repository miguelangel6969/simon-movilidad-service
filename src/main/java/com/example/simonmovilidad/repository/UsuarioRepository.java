package com.example.simonmovilidad.repository;

import com.example.simonmovilidad.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByNombreAndContrasena (String user, String pass);

}
