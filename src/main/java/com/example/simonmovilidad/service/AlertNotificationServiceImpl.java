package com.example.simonmovilidad.service;

import com.example.simonmovilidad.model.Alerta;
import com.example.simonmovilidad.model.dto.AlertaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class AlertNotificationServiceImpl implements AlertNotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    public AlertNotificationServiceImpl(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void enviarAlerta(Alerta alerta) {
        AlertaDTO dto = new AlertaDTO(alerta);
        messagingTemplate.convertAndSend("/topic/alertas", dto);
    }

}
