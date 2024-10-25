package com.info.info_gestionderecetas_app.mapper.receta;

import com.info.info_gestionderecetas_app.domain.Paso;
import com.info.info_gestionderecetas_app.domain.Receta;
import com.info.info_gestionderecetas_app.dto.ingrediente.IngredienteDTO;
import com.info.info_gestionderecetas_app.dto.paso.PasoDTO;
import com.info.info_gestionderecetas_app.dto.receta.RecetaDTO;
import com.info.info_gestionderecetas_app.domain.Categoria;
import com.info.info_gestionderecetas_app.mapper.ingrediente.IngredienteMapper;
import com.info.info_gestionderecetas_app.mapper.paso.PasoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RecetaMapperImpl implements RecetaMapper {

    @Autowired
    private IngredienteMapper ingredienteMapper; // Inyectamos el mapper para Ingredientes

    @Autowired
    private PasoMapper pasoMapper; // Inyectamos el mapper para Pasos

    @Override
    public RecetaDTO recetaToRecetaDTO(Receta receta) {
        if (receta == null) {
            return null; // Retornamos null si la receta es null
        }

        // Mapear todos los pasos de la receta a DTOs usando PasoMapper
        List<PasoDTO> pasosDTO = receta.getPasos() != null
                ? receta.getPasos().stream()
                .map(pasoMapper::pasoToPasoDTO) // Convierte cada Paso a PasoDTO
                .collect(Collectors.toList()) // Recolecta los DTOs en una lista
                : new ArrayList<>(); // Si no hay pasos, retorna una lista vacía

        // Manejo de los ingredientes de cada paso, eliminar duplicados y convertirlos a DTOs
        List<IngredienteDTO> todosIngredientes = pasosDTO.stream()
                .flatMap(pasoDTO -> pasoDTO.ingredientes().stream()) // Extrae los ingredientes de cada paso
                .distinct() // Elimina duplicados de ingredientes
                .collect(Collectors.toList()); // Recolecta los ingredientes en una lista

        // Crear y retornar un objeto RecetaDTO
        return new RecetaDTO(
                receta.getId(),
                receta.getNombre(),
                receta.getDescripcion(),
                receta.getCategoria() != null ? receta.getCategoria().getId() : null, // id de la categoría, si existe
                receta.getCategoria() != null ? receta.getCategoria().getNombre() : null, // Nombre de la categoría, si existe
                pasosDTO,
                todosIngredientes,
                receta.getTiempoTotal(),
                receta.getDificultad()
        );
    }

    @Override
    public Receta recetaDTOToReceta(RecetaDTO recetaDTO) {
        if (recetaDTO == null) {
            return null; // Retornamos null si el DTO es null
        }

        // Crear un nuevo objeto Receta
        Receta receta = new Receta();
        receta.setId(recetaDTO.id());
        receta.setNombre(recetaDTO.nombre());
        receta.setDescripcion(recetaDTO.descripcion());
        receta.setDificultad(recetaDTO.dificultad());

        // Si el DTO tiene una categoría, creamos y asignamos la categoría a la receta
        if (recetaDTO.idCategoria() != null) {
            Categoria categoria = new Categoria();
            categoria.setId(recetaDTO.idCategoria());
            receta.setCategoria(categoria);
        }

        // Mapear los pasos del DTO a la entidad Receta usando PasoMapper
        List<Paso> pasos = recetaDTO.pasos() != null
                ? recetaDTO.pasos().stream()
                .map(pasoMapper::pasoDTOToPaso)
                .collect(Collectors.toList())
                : new ArrayList<>();
        receta.setPasos(pasos);

        return receta;
    }

    @Override
    public void actualizarRecetaDesdeDTO(RecetaDTO recetaDTO, Receta receta) {
        if (recetaDTO == null || receta == null) {
            throw new IllegalArgumentException("El DTO o la receta no pueden ser nulos"); // Validación
        }

        // Actualizar nombre y descripción
        receta.setNombre(recetaDTO.nombre());
        receta.setDescripcion(recetaDTO.descripcion());

        // Actualizar la categoría si se proporciona en el DTO
        if (recetaDTO.idCategoria() != null) {
            Categoria categoria = new Categoria();
            categoria.setId(recetaDTO.idCategoria());
            receta.setCategoria(categoria);
        }

        // Actualizar los pasos si se proporcionan en el DTO
        if (recetaDTO.pasos() != null) {
            List<Paso> pasos = recetaDTO.pasos().stream()
                    .map(pasoMapper::pasoDTOToPaso) // Convierte cada PasoDTO a Paso
                    .collect(Collectors.toList()); // Recolecta los pasos en una lista
            receta.setPasos(pasos); // Asigna los pasos actualizados a la receta
        }
    }
}
