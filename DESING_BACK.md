# DESIGN.md – Backend (Spring Boot + MySQL)

## 💡 Elección de tecnologías

### ☑ Spring Boot 3
- Framework robusto, modular y ampliamente adoptado en la industria.
- Soporte moderno para JWT, WebSockets y REST APIs.
- Integración fácil con bases de datos, seguridad y herramientas DevOps.

### ☑ Java 21
- Versión LTS moderna, con mejoras en rendimiento y sintaxis.
- Permite usar características como `record`, `switch` mejorado y mejores patrones.

### ☑ MySQL
- Base de datos relacional ampliamente conocida.
- Mejores herramientas de administración que SQLite.
- Uso recomendado cuando se requiere persistencia duradera y transacciones.

---

## 🔄 Estructura de paquetes

```
com.simonmovilidad
├── auth
│   └── JwtUtil.java
├── controller
│   └── SensorDataController.java
├── model
│   ├── Vehiculo.java
│   ├── SensorData.java
│   └── Alerta.java
├── repository
│   ├── VehiculoRepository.java
│   ├── SensorRepository.java
│   └── AlertaRepository.java
├── service
│   ├── AutonomiaService.java
│   └── AlertNotificationServiceImpl.java
└── SimomovilidadApplication.java
```

---

## ⚖️ Seguridad y autenticación

- Se implementó autenticación JWT sin librerías externas.
- El token incluye el campo `role` y se propaga por header `Authorization: Bearer <token>`.
- Se añadió una utilidad `JwtUtil.java` para generar y validar tokens manualmente.

---

## 🚨 Lógica de negocio

- Recepción de datos de sensores por API REST (`POST /api/sensores`):
  - Guarda datos históricos
  - Calcula autonomía restante
  - Si es menor a 1 hora → crea una alerta
  - Envía alerta por WebSocket
  - También se envía ubicación actual

---

## 🔔 WebSocket

- Canales:
  - `/topic/vehiculos` → actualizaciones en vivo de ubicaciones
  - `/topic/alertas` → alertas de autonomía baja
- Se usó STOMP sobre SockJS

---

## 📊 Base de datos relacional

Se usó MySQL con las siguientes tablas:

- `vehiculos`
- `sensor_data`
- `alertas`

Relaciones:
- Un vehículo tiene múltiples registros de sensores
- Una alerta está asociada a un vehículo

---

## ⚙️ Utilidades adicionales

- Clase `AutonomiaService` con lógica para calcular si un vehículo está por quedarse sin combustible.
- `AlertNotificationServiceImpl` envía alertas en tiempo real.
- Controladores organizados con validaciones y respuestas limpias.

---

## 📝 Justificación general

Spring Boot + MySQL es una elección sólida para construir APIs empresariales que requieran:
- Seguridad
- Persistencia confiable
- Tiempo real
- Escalabilidad

Además, permite pruebas unitarias, documentación Swagger y despliegue en entornos como AWS o Render.

