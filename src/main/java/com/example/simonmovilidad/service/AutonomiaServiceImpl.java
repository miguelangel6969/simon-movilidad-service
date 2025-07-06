package com.example.simonmovilidad.service;

import org.springframework.stereotype.Service;

@Service
public class AutonomiaServiceImpl implements AutonomiaService  {

    @Override
    public double calcularAutonomia(double nivelCombustible, double consumoPorHora) {
        double litrosRestantes = (nivelCombustible / 100.0) * 50;
        return litrosRestantes / consumoPorHora;
    }

    @Override
    public boolean esBajaAutonomia(double autonomiaHoras) {
        return autonomiaHoras < 1.0;
    }
}