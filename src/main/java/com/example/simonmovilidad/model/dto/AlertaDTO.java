package com.example.simonmovilidad.model.dto;

import com.example.simonmovilidad.model.Alerta;

public class AlertaDTO {
    private Integer id;
    private String mensaje;
    private String fecha;
    private String placaVehiculo;

    public AlertaDTO(Alerta alerta) {
        this.id = alerta.getIdAlerta();
        this.mensaje = alerta.getMensaje();
        this.fecha = alerta.getFecha().toString();
        this.placaVehiculo = alerta.getVehiculo().getPlaca(); // Asegúrate de que no sea null
    }

    // Getters y setters
    public Integer getId() {
        return id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getFecha() {
        return fecha;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }
}

