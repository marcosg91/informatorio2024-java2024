package com.info.app.proyectoapp.service.proyecto;

import com.info.app.proyectoapp.domain.Proyecto;
import com.info.app.proyectoapp.dto.proyecto.ProyectoCreateDto;
import com.info.app.proyectoapp.dto.proyecto.ProyectoCreatedDto;
import com.info.app.proyectoapp.dto.proyecto.ProyectoDto;
import com.info.app.proyectoapp.dto.proyecto.ProyectoUpdatedDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProyectoService {

    Proyecto getProyectoById(UUID uuid);

    Optional<ProyectoDto> getProyectoDtoById(UUID uuid);

    Optional<ProyectoUpdatedDto> closeProyecto(UUID uuid);

    Optional<ProyectoCreatedDto> createProyecto(ProyectoCreateDto proyectoCreateDto);

    List<ProyectoDto> getAllProyectos();
}