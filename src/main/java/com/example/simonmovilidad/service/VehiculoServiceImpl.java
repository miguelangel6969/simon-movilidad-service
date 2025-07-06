package com.example.simonmovilidad.service;

import com.example.simonmovilidad.model.SensorData;
import com.example.simonmovilidad.model.Vehiculo;
import com.example.simonmovilidad.model.dto.VehiculoUbicacionDTO;
import com.example.simonmovilidad.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehiculoServiceImpl implements VehiculoService {

    @Autowired
    private VehiculoRepository vehiculoRepo;

    public List<VehiculoUbicacionDTO> obtenerUbicaciones() {
        List<VehiculoUbicacionDTO> ubicaciones = new ArrayList<>();

        for (Vehiculo v : vehiculoRepo.findAll()) {

            // Verificamos si tiene datos de sensores registrados
            List<SensorData> sensores = v.getDatosSensores();
            if (sensores == null || sensores.isEmpty()) continue;

            // Buscar el SensorData más reciente (por fecha)
            SensorData masReciente = sensores.get(0);
            for (SensorData s : sensores) {
                if (s.getFecha().isAfter(masReciente.getFecha())) {
                    masReciente = s;
                }
            }

            // Creamos y agregamos el DTO con la última posición
            VehiculoUbicacionDTO dto = new VehiculoUbicacionDTO(
                    v.getIdVehiculo(),
                    v.getPlaca(),
                    masReciente.getLatitud(),
                    masReciente.getLongitud()
            );
            ubicaciones.add(dto);
        }

        return ubicaciones;
    }
}
