package com.info.info_gestionderecetas_app.service.ingrediente;

import com.info.info_gestionderecetas_app.domain.Ingrediente;
import com.info.info_gestionderecetas_app.domain.Paso;
import com.info.info_gestionderecetas_app.dto.ingrediente.IngredienteDTO;
import com.info.info_gestionderecetas_app.mapper.ingrediente.IngredienteMapper;
import com.info.info_gestionderecetas_app.repository.ingrediente.IngredienteRepository;
import com.info.info_gestionderecetas_app.repository.paso.PasoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor // Lombok para generar un constructor con todos los argumentos requeridos
public class IngredienteServiceImpl implements IngredienteService {

    // Inyección de dependencias
    private final IngredienteMapper ingredienteMapper;
    private final IngredienteRepository ingredienteRepository;
    private final PasoRepository pasoRepository;


    @Override
    public IngredienteDTO crearIngrediente(IngredienteDTO ingredienteDTO) {
        // Convertir el DTO en una entidad Ingrediente usando el mapper
        Ingrediente ingrediente = ingredienteMapper.ingredienteDTOToIngrediente(ingredienteDTO);

        // Guardar el ingrediente en el repositorio
        Ingrediente ingredienteGuardado = ingredienteRepository.save(ingrediente);

        // Convertir la entidad guardada nuevamente en un DTO para retornar
        return ingredienteMapper.ingredienteToIngredienteDTO(ingredienteGuardado);
    }


    @Override
    public void eliminarIngrediente(UUID id) {
        // Verificar si el ingrediente existe antes de eliminarlo
        if (!ingredienteRepository.existsById(id)) {
            throw new RuntimeException("Ingrediente no encontrado con ID: " + id);
        }

        // Eliminar el ingrediente por su ID
        ingredienteRepository.deleteById(id);
    }


    @Override
    public List<IngredienteDTO> obtenerIngredientesPorReceta(UUID recetaId) {
        // Obtener todos los pasos asociados a la receta desde el repositorio
        List<Paso> pasos = pasoRepository.findByRecetaId(recetaId);

        // Agregar un log para verificar la cantidad de pasos encontrados
        System.out.println("Pasos encontrados: " + pasos.size());

        // Obtener los ingredientes de cada paso, eliminando duplicados
        return pasos.stream()
                .flatMap(paso -> paso.getIngredientes().stream())
                .distinct() // Evitar duplicados
                .map(ingredienteMapper::ingredienteToIngredienteDTO) // Convertir a DTO
                .collect(Collectors.toList());
    }


    @Override
    public List<IngredienteDTO> obtenerIngredientesPorPaso(UUID pasoId) {
        // Buscar ingredientes asociados al paso por su ID y convertirlos en DTO
        return ingredienteRepository.findByPasos_Id(pasoId).stream()
                .map(ingredienteMapper::ingredienteToIngredienteDTO) // Convertir cada ingrediente a DTO
                .collect(Collectors.toList());
    }


    @Override
    public IngredienteDTO crearIngredienteEnPaso(UUID pasoId, IngredienteDTO ingredienteDTO) {
        // Buscar el paso por su ID, lanzar excepción si no se encuentra
        Paso paso = pasoRepository.findById(pasoId)
                .orElseThrow(() -> new RuntimeException("Paso no encontrado con ID: " + pasoId));

        // Convertir el DTO a entidad Ingrediente usando el mapper
        Ingrediente ingrediente = ingredienteMapper.ingredienteDTOToIngrediente(ingredienteDTO);

        // Verificar si el ingrediente ya existe en el repositorio
        Optional<Ingrediente> existingIngrediente = ingredienteRepository.findByNombre(ingrediente.getNombre());

        // Si el ingrediente ya existe, usar el existente. Si no, guardar el nuevo.
        if (existingIngrediente.isPresent()) {
            ingrediente = existingIngrediente.get();
        } else {
            ingrediente = ingredienteRepository.save(ingrediente);
        }

        // Agregar el ingrediente al paso si aún no está asociado
        if (!paso.getIngredientes().contains(ingrediente)) {
            paso.getIngredientes().add(ingrediente); // Asociar ingrediente al paso
            ingrediente.getPasos().add(paso); // Asociar el paso al ingrediente
        }

        // Guardar los cambios en el paso
        pasoRepository.save(paso);

        // Retornar el ingrediente mapeado a DTO
        return ingredienteMapper.ingredienteToIngredienteDTO(ingrediente);
    }


    @Override
    public boolean pasoExiste(UUID pasoId) {
        // Verificar si el paso existe en la base de datos
        return pasoRepository.existsById(pasoId);
    }


    @Override
    public List<IngredienteDTO> obtenerTodosLosIngredientes() {
        // Buscar todos los ingredientes en el repositorio y convertirlos a DTO
        return ingredienteRepository.findAll().stream()
                .map(ingredienteMapper::ingredienteToIngredienteDTO) // Convertir a DTO cada ingrediente
                .collect(Collectors.toList());
    }
}
