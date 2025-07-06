# SETUP.md – Backend (Spring Boot + MySQL)

## 🚀 Requisitos

- Java 21
- Maven 3.x
- Git
- MySQL (local o remoto)

---

## 📁 Clonar el proyecto

```bash
git clone https://github.com/miguelangel6969/simon-movilidad-service.git
cd simon-movilidad-service
```

---

## ⚙️ Configuración de la base de datos

### 1. Crear base de datos `mishone`

```sql
CREATE DATABASE mishone;
```

### 2. Configurar credenciales en `application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/mishone
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
```

---

## ⚡ Construir y ejecutar

### Opción 1: Desde terminal

```bash
mvn clean install
mvn spring-boot:run
```

### Opción 2: Desde un IDE

- Abrir el proyecto con IntelliJ IDEA o Eclipse.
- Ejecutar la clase `SimomovilidadApplication.java` como aplicación Spring Boot.

---

## 👤 Credenciales por defecto

```
Usuario: migueladmin
Contraseña: 1234
Rol: ADMIN
```

---

## 🔐 Token JWT

Para autenticarse, el frontend debe hacer login con el endpoint:

```http
POST /api/login
Content-Type: application/json

{
  "username": "migueladmin",
  "password": "1234"
}
```

Recibirá un token JWT que debe incluir en cada petición como:

```
Authorization: Bearer <token>
```

---

## 📡 WebSocket

- URL: `/ws` (ej. `http://localhost:8080/ws-alertas`)
- Topic alertas: `/topic/alertas`
- Topic vehículos: `/topic/vehiculos`

---

## 💾 Base de datos MySQL

Los datos se almacenan en la base de datos `mishone` usando el esquema generado por JPA/Hibernate.

Puedes visualizar y administrar la base de datos con herramientas como:

- MySQL Workbench
- DBeaver
- TablePlus

---

## ✅ Pruebas unitarias

```bash
mvn test
```

Incluye pruebas para:
- `JwtUtil`
- `AutonomiaService`
- `AlertNotificationService`

---

## ⚙️ Variables de entorno (Render/AWS)

Si se despliega en la nube, configurar:

- `DB_HOST`, `DB_USER`, `DB_PASSWORD`: credenciales para conexión a MySQL.
- `SECRET`: clave usada para firmar JWT (actualmente hardcodeada, ideal extraerla).

