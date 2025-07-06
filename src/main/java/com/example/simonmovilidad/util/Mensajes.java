package com.example.simonmovilidad.util;

/**
 * 📦 Mensajes constantes reutilizables en todo el sistema
 */

public interface Mensajes {

    // 🛑 Errores de autenticación
    String ERROR_NO_AUTORIZADO = "No autorizado";
    String ERROR_TOKEN_INVALIDO = "Token inválido o ausente";
    String ERROR_ACCESO_SOLO_ADMIN = "Solo ADMIN puede acceder a este recurso";
    String CREDENCIALES_INVALIDAS = "Credenciales inválidas. Verifica tu usuario y contraseña";
    String USUARIO_INACTIVO = "Usuario inactivo. Contacta al administrador";
    // 🚨 Alertas
    String ALERTA_BAJA_AUTONOMIA = "⚠️ Autonomía menor a 1 hora. Verifica combustible.";

    // ✅ Mensajes generales
    String OPERACION_EXITOSA = "Operación realizada con éxito";
    String DATOS_GUARDADOS = "Datos guardados correctamente";
    String VEHICULO_NO_ENCONTRADO = "Vehículo no encontrado";
    String USUARIO_NO_ENCONTRADO = "Usuario no encontrado";
    String ROL_NO_AUTORIZADO = "Rol no autorizado para esta acción";
    String ERROR_INTERNO_SERVIDOR = "Error interno del servidor";

    // 🛠️ Validaciones
    String CAMPOS_OBLIGATORIOS = "Todos los campos son obligatorios";
    String FORMATO_INVALIDO = "El formato de los datos no es válido";
}

