package com.example.simonmovilidad.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "vehiculos")
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vehiculo")
    private Integer idVehiculo;

    @Column(name = "placa", nullable = false, length = 20, unique = true)
    private String placa;

    @Column(name = "nombre", length = 100)
    private String nombre;

    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<SensorData> datosSensores;

    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Alerta> alertas;

}

