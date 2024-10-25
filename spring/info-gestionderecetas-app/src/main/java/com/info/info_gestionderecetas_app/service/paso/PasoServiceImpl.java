package com.info.info_gestionderecetas_app.service.paso;

import com.info.info_gestionderecetas_app.domain.Paso;
import com.info.info_gestionderecetas_app.domain.Receta;
import com.info.info_gestionderecetas_app.dto.paso.PasoDTO;
import com.info.info_gestionderecetas_app.mapper.ingrediente.IngredienteMapper;
import com.info.info_gestionderecetas_app.mapper.paso.PasoMapper;
import com.info.info_gestionderecetas_app.repository.paso.PasoRepository;
import com.info.info_gestionderecetas_app.repository.receta.RecetaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor // Lombok para generar un constructor con todos los argumentos
public class PasoServiceImpl implements PasoService {

    // Inyección de dependencias
    private final PasoMapper pasoMapper;
    private final PasoRepository pasoRepository;
    private final RecetaRepository recetaRepository;
    private final IngredienteMapper ingredienteMapper;


    @Override
    public PasoDTO crearPaso(UUID recetaId, PasoDTO pasoDTO) {
        // Buscar la receta por su ID, lanzar excepción si no se encuentra
        Receta receta = recetaRepository.findById(recetaId)
                .orElseThrow(() -> new RuntimeException("Receta no encontrada"));

        // Convertir el DTO en una entidad Paso usando el mapper
        Paso paso = pasoMapper.pasoDTOToPaso(pasoDTO);
        paso.setReceta(receta); // Asociar el paso con la receta

        // Si el DTO contiene una lista de ingredientes, mapearlos y asociarlos al paso
        if (pasoDTO.ingredientes() != null) {
            paso.setIngredientes(pasoDTO.ingredientes().stream()
                    .map(ingredienteMapper::ingredienteDTOToIngrediente) // Convertir cada DTO en entidad Ingrediente
                    .collect(Collectors.toList())); // Convertir a lista
        }

        // Guardar el paso en el repositorio
        Paso pasoGuardado = pasoRepository.save(paso);

        // Retornar el paso guardado mapeado a DTO
        return pasoMapper.pasoToPasoDTO(pasoGuardado);
    }


    @Override
    public void eliminarPaso(UUID recetaId, UUID pasoId) {
        // Verifica si la receta existe en el repositorio
        Receta receta = recetaRepository.findById(recetaId)
                .orElseThrow(() -> new RuntimeException("Receta no encontrada"));

        // Verifica si el paso existe en la receta
        Paso paso = pasoRepository.findById(pasoId)
                .orElseThrow(() -> new RuntimeException("Paso no encontrado"));

        // Elimina el paso del repositorio
        pasoRepository.delete(paso);
    }
}
