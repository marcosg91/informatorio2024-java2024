package com.info.info_gestionderecetas_app.mapper.ingrediente;

import com.info.info_gestionderecetas_app.domain.Ingrediente;
import com.info.info_gestionderecetas_app.dto.ingrediente.IngredienteDTO;
import org.springframework.stereotype.Component;

@Component
public class IngredienteMapperImpl implements IngredienteMapper {

    // Convierte un IngredienteDTO en una entidad Ingrediente
    @Override
    public Ingrediente ingredienteDTOToIngrediente(IngredienteDTO ingredienteDTO) {
        // Verifica si el IngredienteDTO es nulo
        if (ingredienteDTO == null) {
            return null; // Si es nulo, retorna nulo
        }

        // Crea una nueva instancia de Ingrediente
        Ingrediente ingrediente = new Ingrediente();

        // Asigna los valores del DTO a la entidad Ingrediente
        ingrediente.setId(ingredienteDTO.id());
        ingrediente.setNombre(ingredienteDTO.nombre());
        ingrediente.setDescripcion(ingredienteDTO.descripcion());

        // Retorna la entidad Ingrediente
        return ingrediente;
    }

    // Convierte una entidad Ingrediente en un IngredienteDTO
    @Override
    public IngredienteDTO ingredienteToIngredienteDTO(Ingrediente ingrediente) {
        // Verifica si la entidad Ingrediente es nula
        if (ingrediente == null) {
            return null; // Si es nula, retorna nulo
        }

        // Retorna un nuevo IngredienteDTO usando los valores de la entidad Ingrediente
        return new IngredienteDTO(
                ingrediente.getId(),
                ingrediente.getNombre(),
                ingrediente.getDescripcion()
        );
    }
}
