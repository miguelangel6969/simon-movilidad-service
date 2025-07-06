-- simonmovilidad.roles definition

CREATE TABLE `roles` (
  `id_rol` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- simonmovilidad.vehiculos definition

CREATE TABLE `vehiculos` (
  `id_vehiculo` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `placa` varchar(20) NOT NULL,
  PRIMARY KEY (`id_vehiculo`),
  UNIQUE KEY `UKa8w6omovfa10q8eyjalqas391` (`placa`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- simonmovilidad.alertas definition

CREATE TABLE `alertas` (
  `id_alerta` int NOT NULL AUTO_INCREMENT,
  `fecha` datetime(6) DEFAULT NULL,
  `leida` bit(1) DEFAULT NULL,
  `mensaje` varchar(255) NOT NULL,
  `vehiculo_id` int NOT NULL,
  PRIMARY KEY (`id_alerta`),
  KEY `FK7fokqq6m2g8vvvmg4v6i92fr4` (`vehiculo_id`),
  CONSTRAINT `FK7fokqq6m2g8vvvmg4v6i92fr4` FOREIGN KEY (`vehiculo_id`) REFERENCES `vehiculos` (`id_vehiculo`)
) ENGINE=InnoDB AUTO_INCREMENT=236 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- simonmovilidad.sensor_data definition

CREATE TABLE `sensor_data` (
  `id_sensor_data` int NOT NULL AUTO_INCREMENT,
  `consumo_por_hora` double DEFAULT NULL,
  `fecha` datetime(6) DEFAULT NULL,
  `latitud` double DEFAULT NULL,
  `longitud` double DEFAULT NULL,
  `nivel_combustible` double DEFAULT NULL,
  `temperatura` double DEFAULT NULL,
  `velocidad` double DEFAULT NULL,
  `vehiculo_id` int NOT NULL,
  PRIMARY KEY (`id_sensor_data`),
  KEY `FKer2b3gv8095mh33skpr8twrj9` (`vehiculo_id`),
  CONSTRAINT `FKer2b3gv8095mh33skpr8twrj9` FOREIGN KEY (`vehiculo_id`) REFERENCES `vehiculos` (`id_vehiculo`)
) ENGINE=InnoDB AUTO_INCREMENT=630 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- simonmovilidad.usuarios definition

CREATE TABLE `usuarios` (
  `id_usuario` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `contrasena` varchar(255) NOT NULL,
  `id_rol` int NOT NULL,
  PRIMARY KEY (`id_usuario`),
  KEY `usuarios_roles_FK` (`id_rol`),
  CONSTRAINT `usuarios_roles_FK` FOREIGN KEY (`id_rol`) REFERENCES `roles` (`id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



-- 🚦 Insertar roles
INSERT INTO roles (id_rol, nombre) VALUES
  (1, 'ADMIN'),
  (2, 'USER');

-- 👤 Insertar usuarios
-- Asegúrate que las contraseñas estén hasheadas si usas BCrypt (esto es texto plano solo para pruebas)
INSERT INTO usuarios (id_usuario, nombre, contrasena, id_rol) VALUES
  (1, 'migueladmin', '1234', 1),
  (2, 'migueluser', '1234', 2); 

-- 🚗 Insertar vehículo
INSERT INTO vehiculos (id_vehiculo, nombre, placa) VALUES
  (1, 'Vehículo de prueba', 'ABC123');








