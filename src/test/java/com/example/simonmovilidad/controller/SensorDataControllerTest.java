package com.example.simonmovilidad.controller;

import com.example.simonmovilidad.model.Alerta;
import com.example.simonmovilidad.model.Vehiculo;
import com.example.simonmovilidad.model.dto.SensorDTO;
import com.example.simonmovilidad.repository.AlertaRepository;
import com.example.simonmovilidad.repository.SensorDataRepository;
import com.example.simonmovilidad.repository.VehiculoRepository;
import com.example.simonmovilidad.service.AlertNotificationService;
import com.example.simonmovilidad.service.AutonomiaService;
import com.example.simonmovilidad.util.Mensajes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SensorDataControllerTest {

    @InjectMocks
    private SensorDataController controller;

    @Mock
    private VehiculoRepository vehiculoRepo;

    @Mock
    private SensorDataRepository sensorRepo;

    @Mock
    private SimpMessagingTemplate messagingTemplate;

    @Mock
    private AlertaRepository alertaRepo;

    @Mock
    private AlertNotificationService alertService;

    @Mock
    private AutonomiaService autonomiaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGeneraAlertaPorBajaAutonomia() {
        SensorDTO dto = new SensorDTO();
        dto.setVehiculoId(1);
        dto.setNivelCombustible(5); // 5%
        dto.setConsumoPorHora(6);
        dto.setLatitud(0); dto.setLongitud(0); dto.setTemperatura(0); dto.setVelocidad(0);

        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setIdVehiculo(1);
        when(vehiculoRepo.findById(1)).thenReturn(Optional.of(vehiculo));

        // ✅ simulación del servicio de autonomía
        when(autonomiaService.calcularAutonomia(5.0, 6.0)).thenReturn(0.41);
        when(autonomiaService.esBajaAutonomia(0.41)).thenReturn(true);

        ResponseEntity<?> respuesta = controller.recibirDatos(dto);

        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals(Mensajes.DATOS_GUARDADOS, respuesta.getBody());
        verify(alertaRepo).save(any(Alerta.class));
        verify(alertService).enviarAlerta(any(Alerta.class));
    }

    @Test
    void testVehiculoNoExiste() {
        SensorDTO dto = new SensorDTO();
        dto.setVehiculoId(999);
        when(vehiculoRepo.findById(999)).thenReturn(Optional.empty());

        ResponseEntity<?> respuesta = controller.recibirDatos(dto);
        assertEquals(400, respuesta.getStatusCodeValue());
        assertEquals(Mensajes.VEHICULO_NO_ENCONTRADO, respuesta.getBody());
    }
}