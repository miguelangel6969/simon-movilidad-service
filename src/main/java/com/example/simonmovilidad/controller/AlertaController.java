package com.example.simonmovilidad.controller;


import com.example.simonmovilidad.auth.JwtAuthUtil;
import com.example.simonmovilidad.model.Alerta;
import com.example.simonmovilidad.model.SensorData;
import com.example.simonmovilidad.model.Vehiculo;
import com.example.simonmovilidad.repository.AlertaRepository;
import com.example.simonmovilidad.repository.SensorDataRepository;
import com.example.simonmovilidad.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.simonmovilidad.auth.JwtUtil;
import com.example.simonmovilidad.util.Mensajes;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/vehiculos")
public class AlertaController {

    @Autowired
    private VehiculoRepository vehiculoRepo;

    @Autowired
    private SensorDataRepository sensorRepo;

    @Autowired
    private AlertaRepository alertaRepo;

    @GetMapping("/{id}/alertas")
    public ResponseEntity<?> obtenerAlertas(@PathVariable Integer id, HttpServletRequest request) {

        if (!JwtAuthUtil.esAdmin(request)) {
            return ResponseEntity.status(403).body(Mensajes.ROL_NO_AUTORIZADO);
        }

        Vehiculo vehiculo = vehiculoRepo.findById(id).orElse(null);
        if (vehiculo == null) {
            return ResponseEntity.badRequest().body(Mensajes.VEHICULO_NO_ENCONTRADO);
        }

        List<Alerta> alertas = alertaRepo.findAlertasByVehiculoId(id);
        return ResponseEntity.ok(alertas);
    }

    @GetMapping("/{id}/sensores")
    public ResponseEntity<?> obtenerSensores(@PathVariable Integer id) {
        Vehiculo vehiculo = vehiculoRepo.findById(id).orElse(null);
        if (vehiculo == null) {
            return ResponseEntity.badRequest().body(Mensajes.VEHICULO_NO_ENCONTRADO);
        }

        // Obtener los últimos 20 registros ordenados por fecha descendente
        List<SensorData> datos = sensorRepo.findSensorDataByVehiculoId(id);
        return ResponseEntity.ok(datos);
    }


}