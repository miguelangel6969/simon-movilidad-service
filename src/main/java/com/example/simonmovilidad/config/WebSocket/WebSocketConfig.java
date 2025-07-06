package com.example.simonmovilidad.config.WebSocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

// 📦 Indica que esta clase proporciona configuración para Spring
@Configuration

// 📡 Habilita soporte para WebSocket con STOMP (protocolo de mensajería)
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * 📬 Configura el "message broker", es decir:
     *  - Dónde se suscriben los clientes
     *  - Qué prefijos usarán para enviar mensajes
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // ✅ Los clientes se suscriben a este destino para recibir mensajes del servidor
        // Ej: client.subscribe("/topic/alertas")
        config.enableSimpleBroker("/topic");

        // 📤 Cuando un cliente envía un mensaje al servidor, debe usar este prefijo
        // Ej: client.send("/app/enviar", payload)
        config.setApplicationDestinationPrefixes("/app");
    }

    /**
     * 🌐 Registra el endpoint WebSocket para que los clientes puedan conectarse
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 🧩 Define la URL para conectarse vía WebSocket (STOMP)
        // Ej: ws://localhost:8080/ws-alertas
        // El `.withSockJS()` permite compatibilidad con navegadores antiguos (fallbacks)
        registry.addEndpoint("/ws-alertas")
                .setAllowedOriginPatterns("*") // 🔓 Permite cualquier origen (cuidado en producción)
                .withSockJS();                 // Habilita soporte para SockJS
    }
}
