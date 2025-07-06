package com.example.simonmovilidad.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "roles")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol", updatable = false, nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, unique = true, length = 50)
    String nombre;

}
