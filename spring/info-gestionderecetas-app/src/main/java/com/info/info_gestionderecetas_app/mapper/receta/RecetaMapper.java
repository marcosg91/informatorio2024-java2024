package com.info.info_gestionderecetas_app.mapper.receta;

import com.info.info_gestionderecetas_app.domain.Receta;
import com.info.info_gestionderecetas_app.dto.receta.RecetaDTO;

public interface RecetaMapper {
    RecetaDTO recetaToRecetaDTO(Receta receta);
    Receta recetaDTOToReceta(RecetaDTO recetaDTO);
    void actualizarRecetaDesdeDTO(RecetaDTO recetaDTO, Receta receta);
}
