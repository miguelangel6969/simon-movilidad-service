package com.example.simonmovilidad.service;


import com.example.simonmovilidad.model.Alerta;
import com.example.simonmovilidad.model.Vehiculo;
import com.example.simonmovilidad.model.dto.AlertaDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.messaging.simp.SimpMessagingTemplate;


import static org.mockito.Mockito.*;

class AlertNotificationServiceImplTest {

    @InjectMocks
    private AlertNotificationServiceImpl service;

    @Mock
    private SimpMessagingTemplate messagingTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testEnviarAlertaLlamaConvertAndSend() {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setIdVehiculo(1);
        vehiculo.setPlaca("ABC123");

        Alerta alerta = new Alerta();
        alerta.setIdAlerta(1);
        alerta.setMensaje("⚠️ Autonomía baja");
        alerta.setVehiculo(vehiculo); // ✅ Asegúrate de setearlo

        service.enviarAlerta(alerta);

        // ✅ Verificamos que se envíe al tópico correcto
        verify(messagingTemplate).convertAndSend(eq("/topic/alertas"), any(AlertaDTO.class));
    }
}
