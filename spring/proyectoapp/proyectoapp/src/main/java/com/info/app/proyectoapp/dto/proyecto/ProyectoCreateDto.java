package com.info.app.proyectoapp.dto.proyecto;

import java.util.List;
import java.util.UUID;

public record ProyectoCreateDto( String nombre, List<UUID> colaboradoresId, UUID liderId  ) {
}