package com.info.info_gestionderecetas_app.mapper.receta;

import com.info.info_gestionderecetas_app.domain.Receta;
import com.info.info_gestionderecetas_app.dto.receta.RecetaDTO;

public interface RecetaMapper {

    Receta recetaDTOToReceta( RecetaDTO recetaDTO);

    RecetaDTO recetaToRecetaDTO( Receta receta);
}
