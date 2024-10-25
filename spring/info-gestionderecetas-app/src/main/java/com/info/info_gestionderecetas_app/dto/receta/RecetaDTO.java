package com.info.info_gestionderecetas_app.dto.receta;

import com.info.info_gestionderecetas_app.domain.enums.Dificultad;
import com.info.info_gestionderecetas_app.dto.paso.PasoDTO;
import com.info.info_gestionderecetas_app.dto.ingrediente.IngredienteDTO;

import java.util.List;
import java.util.UUID;

public record RecetaDTO(
        UUID id,
        String nombre,
        String descripcion,
        UUID idCategoria,
        String categoriaNombre,//
        List<PasoDTO> pasos,
        List<IngredienteDTO> ingredientes,
        int tiempoTotal,
        Dificultad dificultad
) {}
