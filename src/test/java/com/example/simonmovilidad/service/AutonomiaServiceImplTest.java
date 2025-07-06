package com.example.simonmovilidad.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AutonomiaServiceImplTest {

    private final AutonomiaServiceImpl service = new AutonomiaServiceImpl();

    @Test
    void testAutonomiaBaja() {
        double autonomia = service.calcularAutonomia(5, 6); // 5% de 50 = 2.5 / 6 = 0.41h
        assertTrue(service.esBajaAutonomia(autonomia));
    }

    @Test
    void testAutonomiaNormal() {
        double autonomia = service.calcularAutonomia(60, 6); // 60% de 50 = 30 / 6 = 5h
        assertFalse(service.esBajaAutonomia(autonomia));
    }

    @Test
    void testCalculoExacto() {
        double autonomia = service.calcularAutonomia(20, 10); // 20% = 10 litros / 10 = 1h
        assertEquals(1.0, autonomia, 0.01);
    }
}


