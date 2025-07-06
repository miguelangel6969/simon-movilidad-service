package com.example.simonmovilidad.controller;

import com.example.simonmovilidad.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    @GetMapping("/posiciones")
    public ResponseEntity<?> obtenerUbicaciones() {
        return ResponseEntity.ok(vehiculoService.obtenerUbicaciones());
    }
}