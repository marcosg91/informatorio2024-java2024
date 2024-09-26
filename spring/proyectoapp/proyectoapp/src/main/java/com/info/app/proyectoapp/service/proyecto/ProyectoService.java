package com.info.app.proyectoapp.service.proyecto;

import com.info.app.proyectoapp.domain.Proyecto;

import java.util.UUID;

public interface ProyectoService {

    Proyecto getProyectoById(UUID id);
}
