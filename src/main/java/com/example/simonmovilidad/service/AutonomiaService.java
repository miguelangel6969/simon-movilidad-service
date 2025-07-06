package com.example.simonmovilidad.service;

public interface AutonomiaService {

    double calcularAutonomia (double nivelCombustible, double consumoPorHora);

    boolean esBajaAutonomia(double autonomiaHoras);
}
