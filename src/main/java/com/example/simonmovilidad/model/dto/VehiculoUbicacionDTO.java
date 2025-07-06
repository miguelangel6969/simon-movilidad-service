package com.example.simonmovilidad.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class VehiculoUbicacionDTO {
    private Integer id;
    private String placa;
    private double latitud;
    private double longitud;

    public VehiculoUbicacionDTO(Integer id, String placa, double latitud, double longitud) {
        this.id = id;
        this.placa = placa;
        this.latitud = latitud;
        this.longitud = longitud;
    }

}