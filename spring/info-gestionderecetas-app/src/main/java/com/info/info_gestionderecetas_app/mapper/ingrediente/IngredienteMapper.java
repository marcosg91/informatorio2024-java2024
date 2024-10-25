package com.info.info_gestionderecetas_app.mapper.ingrediente;

import com.info.info_gestionderecetas_app.domain.Ingrediente;
import com.info.info_gestionderecetas_app.dto.ingrediente.IngredienteDTO;

public interface IngredienteMapper {
    Ingrediente ingredienteDTOToIngrediente(IngredienteDTO ingredienteDTO);
    IngredienteDTO ingredienteToIngredienteDTO(Ingrediente ingrediente);
}
