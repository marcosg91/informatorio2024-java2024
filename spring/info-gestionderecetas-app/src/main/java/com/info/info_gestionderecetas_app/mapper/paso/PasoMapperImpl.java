package com.info.info_gestionderecetas_app.mapper.paso;

import com.info.info_gestionderecetas_app.domain.Ingrediente;
import com.info.info_gestionderecetas_app.domain.Paso;
import com.info.info_gestionderecetas_app.dto.paso.PasoDTO;
import com.info.info_gestionderecetas_app.dto.ingrediente.IngredienteDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PasoMapperImpl implements PasoMapper {

    // Convierte una entidad Paso en un PasoDTO
    @Override
    public PasoDTO pasoToPasoDTO(Paso paso) {
        // Verifica si el objeto Paso es nulo
        if (paso == null) {
            return null; // Retorna null si el paso no existe
        }

        // Retorna un nuevo PasoDTO con los valores del Paso
        return new PasoDTO(
                paso.getId(),
                paso.getDescripcion(),
                paso.getTiempo(),
                paso.isEsOpcional(),
                mapearIngredientes(paso.getIngredientes()) // Mapea los ingredientes del paso a IngredienteDTO
        );
    }

    // Mapea una lista de entidades Ingrediente a una lista de IngredienteDTO
    private List<IngredienteDTO> mapearIngredientes(List<Ingrediente> ingredientes) {
        // Usamos stream para transformar cada Ingrediente en un IngredienteDTO
        return ingredientes.stream()
                .map(ingrediente -> new IngredienteDTO(
                        ingrediente.getId(),
                        ingrediente.getNombre(),
                        ingrediente.getDescripcion())
                )
                .collect(Collectors.toList()); // Colecciona los resultados en una lista
    }

    // Convierte un PasoDTO en una entidad Paso
    @Override
    public Paso pasoDTOToPaso(PasoDTO pasoDTO) {
        // Verifica si el PasoDTO es nulo
        if (pasoDTO == null) {
            return null; // Retorna null si el DTO no existe
        }

        // Crea una nueva instancia de Paso
        Paso paso = new Paso();
        paso.setId(pasoDTO.id());
        paso.setDescripcion(pasoDTO.descripcion());
        paso.setTiempo(pasoDTO.tiempo());
        paso.setEsOpcional(pasoDTO.esOpcional());

        // Si hay ingredientes en el DTO, mapea cada uno a una entidad Ingrediente
        if (pasoDTO.ingredientes() != null) {
            paso.setIngredientes(pasoDTO.ingredientes().stream()
                    .map(ingredienteDTO -> new Ingrediente(
                            ingredienteDTO.id(),
                            ingredienteDTO.nombre(),
                            ingredienteDTO.descripcion())
                    )
                    .collect(Collectors.toList()));
        }

        return paso; // Retorna el objeto Paso creado
    }

    // Actualiza un Paso existente a partir de un PasoDTO
    @Override
    public void actualizarPasoDesdeDTO(PasoDTO pasoDTO, Paso paso) {
        // Verifica que tanto el DTO como el Paso no sean nulos
        if (pasoDTO == null || paso == null) {
            throw new IllegalArgumentException("El DTO o el paso no pueden ser nulos");
        }

        // Actualiza los campos del paso con los valores del DTO
        paso.setDescripcion(pasoDTO.descripcion());
        paso.setTiempo(pasoDTO.tiempo());
        paso.setEsOpcional(pasoDTO.esOpcional() != null ? pasoDTO.esOpcional() : false);

        // Si hay ingredientes en el DTO, actualiza los ingredientes del paso
        if (pasoDTO.ingredientes() != null) {
            paso.setIngredientes(pasoDTO.ingredientes().stream()
                    .map(ingredienteDTO -> new Ingrediente(
                            ingredienteDTO.id(),
                            ingredienteDTO.nombre(),
                            ingredienteDTO.descripcion())
                    )
                    .collect(Collectors.toList())); // Colecciona los ingredientes actualizados
        }
    }
}
