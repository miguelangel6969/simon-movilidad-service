package com.example.simonmovilidad.controller;


import com.example.simonmovilidad.model.Alerta;
import com.example.simonmovilidad.model.SensorData;
import com.example.simonmovilidad.model.Vehiculo;
import com.example.simonmovilidad.model.dto.SensorDTO;
import com.example.simonmovilidad.model.dto.VehiculoUbicacionDTO;
import com.example.simonmovilidad.repository.AlertaRepository;
import com.example.simonmovilidad.repository.SensorDataRepository;
import com.example.simonmovilidad.repository.VehiculoRepository;
import com.example.simonmovilidad.service.AlertNotificationService;
import com.example.simonmovilidad.service.AutonomiaService;
import com.example.simonmovilidad.util.Mensajes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sensores")
public class SensorDataController {

    @Autowired
    private VehiculoRepository vehiculoRepo;

    @Autowired
    private SensorDataRepository sensorRepo;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private AlertaRepository alertaRepo;

    @Autowired
    private AlertNotificationService alertNotificationService;

    @Autowired
    private AutonomiaService autonomiaService;

    @PostMapping
    public ResponseEntity<?> recibirDatos(@RequestBody SensorDTO dto) {

        Vehiculo vehiculo = vehiculoRepo.findById(dto.getVehiculoId()).orElse(null);
        if (vehiculo == null) {
            return ResponseEntity.badRequest().body(Mensajes.VEHICULO_NO_ENCONTRADO);
        }

        // Guardar datos del sensor
        SensorData data = new SensorData();
        data.setLatitud(dto.getLatitud());
        data.setLongitud(dto.getLongitud());
        data.setTemperatura(dto.getTemperatura());
        data.setVelocidad(dto.getVelocidad());
        data.setNivelCombustible(dto.getNivelCombustible());
        data.setConsumoPorHora(dto.getConsumoPorHora());
        data.setVehiculo(vehiculo);
        sensorRepo.save(data);

        // Calcular autonomía
        double autonomia = autonomiaService.calcularAutonomia(dto.getNivelCombustible(), dto.getConsumoPorHora());

        // Si es baja, genera y envía alerta
        if (autonomiaService.esBajaAutonomia(autonomia)) {
            Alerta alerta = new Alerta();
            alerta.setMensaje(Mensajes.ALERTA_BAJA_AUTONOMIA);
            alerta.setVehiculo(vehiculo);
            alertaRepo.save(alerta);
            alertNotificationService.enviarAlerta(alerta);
        }

        // 🔴 Enviar ubicación en tiempo real por WebSocket
        VehiculoUbicacionDTO ubicacion = new VehiculoUbicacionDTO(
                vehiculo.getIdVehiculo(),
                vehiculo.getPlaca(),
                dto.getLatitud(),
                dto.getLongitud()
        );

        messagingTemplate.convertAndSend("/topic/vehiculos", ubicacion);

        return ResponseEntity.ok(Mensajes.DATOS_GUARDADOS);
    }
}
