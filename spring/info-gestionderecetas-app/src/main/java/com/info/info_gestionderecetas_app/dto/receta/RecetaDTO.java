package com.info.info_gestionderecetas_app.dto.receta;

import com.info.info_gestionderecetas_app.domain.Paso;
import com.info.info_gestionderecetas_app.domain.enums.Dificultad;

import java.util.List;
import java.util.UUID;

public record RecetaDTO(
        String nombre,
        String descripcion,
        Dificultad dificultad,
        UUID idCategoria,
        List<Paso> pasos
) {}
