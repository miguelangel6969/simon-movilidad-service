package com.example.simonmovilidad.repository;

import com.example.simonmovilidad.model.Alerta;
import com.example.simonmovilidad.model.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SensorDataRepository extends JpaRepository<SensorData, Integer> {

    @Query("SELECT s FROM SensorData s WHERE s.vehiculo.idVehiculo = :vehiculoId ORDER BY s.fecha DESC")
    List<SensorData> findSensorDataByVehiculoId(@Param("vehiculoId") Integer vehiculoId);
}
