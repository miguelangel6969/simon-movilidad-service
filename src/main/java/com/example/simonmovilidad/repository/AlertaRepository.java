package com.example.simonmovilidad.repository;

import com.example.simonmovilidad.model.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AlertaRepository extends JpaRepository<Alerta, Integer> {

    @Query("SELECT a FROM Alerta a WHERE a.vehiculo.idVehiculo = :vehiculoId ORDER BY a.fecha DESC")
    List<Alerta> findAlertasByVehiculoId(@Param("vehiculoId") Integer vehiculoId);
}

