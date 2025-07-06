package com.example.simonmovilidad.service;

import com.example.simonmovilidad.model.dto.VehiculoUbicacionDTO;

import java.util.List;

public interface VehiculoService {

    List<VehiculoUbicacionDTO> obtenerUbicaciones();
}
