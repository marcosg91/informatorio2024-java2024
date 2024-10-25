package com.info.info_gestionderecetas_app.service.receta;

import com.info.info_gestionderecetas_app.domain.Categoria;
import com.info.info_gestionderecetas_app.domain.Paso;
import com.info.info_gestionderecetas_app.domain.Receta;
import com.info.info_gestionderecetas_app.domain.Ingrediente;
import com.info.info_gestionderecetas_app.dto.paso.PasoDTO;
import com.info.info_gestionderecetas_app.dto.receta.RecetaDTO;
import com.info.info_gestionderecetas_app.dto.ingrediente.IngredienteDTO;
import com.info.info_gestionderecetas_app.mapper.paso.PasoMapper;
import com.info.info_gestionderecetas_app.mapper.receta.RecetaMapper;
import com.info.info_gestionderecetas_app.mapper.ingrediente.IngredienteMapper;
import com.info.info_gestionderecetas_app.repository.ingrediente.IngredienteRepository;
import com.info.info_gestionderecetas_app.repository.receta.RecetaRepository;
import com.info.info_gestionderecetas_app.repository.categoria.CategoriaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RecetaServiceImpl implements RecetaService {

    // Repositorios y mappers necesarios para las operaciones
    private final RecetaRepository recetaRepository;
    private final RecetaMapper recetaMapper;
    private final PasoMapper pasoMapper;
    private final CategoriaRepository categoriaRepository;
    private final IngredienteMapper ingredienteMapper;
    private final IngredienteRepository ingredienteRepository;

    @Override
    @Transactional
    public RecetaDTO crearReceta(RecetaDTO recetaDTO) {
        // Validar los datos de entrada del DTO de receta
        validarRecetaDTO(recetaDTO);

        // Convertir RecetaDTO a entidad Receta
        Receta recetaCreada = recetaMapper.recetaDTOToReceta(recetaDTO);

        // Obtener la categoría completa desde la base de datos (si se proporcionó un idCategoria)
        if (recetaDTO.idCategoria() != null) {
            Categoria categoria = categoriaRepository.findById(recetaDTO.idCategoria()).orElse(null);
            recetaCreada.setCategoria(categoria);  // Asignar la categoría si se encontró, de lo contrario, será null
        }

        // Persistir y asociar ingredientes a la receta
        List<Ingrediente> ingredientesPersistidos = mapearYGuardarIngredientes(recetaDTO.ingredientes());
        recetaCreada.setIngredientes(ingredientesPersistidos);

        // Asociar pasos a la receta
        recetaCreada.setPasos(mapearPasos(recetaDTO.pasos(), recetaCreada));

        // Calcular el tiempo total de la receta
        calcularTiempoTotalReceta(recetaCreada);

        // Guardar la receta y los pasos asociados
        Receta recetaGuardada = recetaRepository.save(recetaCreada);

        // Mapear la receta guardada a DTO para la respuesta
        return recetaMapper.recetaToRecetaDTO(recetaGuardada);
    }

    // Método para validar el DTO de receta antes de crearla
    private void validarRecetaDTO(RecetaDTO recetaDTO) {
        if (recetaDTO.idCategoria() == null) {
            throw new IllegalArgumentException("El ID de la categoría no puede ser nulo");
        }

        if (!categoriaRepository.existsById(recetaDTO.idCategoria())) {
            throw new RuntimeException("Categoría no encontrada");
        }

        if (recetaDTO.ingredientes() == null || recetaDTO.ingredientes().isEmpty()) {
            throw new IllegalArgumentException("La lista de ingredientes no puede estar vacía");
        }

        if (recetaDTO.pasos() == null || recetaDTO.pasos().isEmpty()) {
            throw new IllegalArgumentException("La lista de pasos no puede estar vacía");
        }
    }

    // Método para mapear y guardar la lista de ingredientes desde el DTO
    private List<Ingrediente> mapearYGuardarIngredientes(List<IngredienteDTO> ingredientesDTO) {
        return ingredientesDTO.stream()
                .map(ingredienteDTO -> {
                    Ingrediente ingrediente = ingredienteMapper.ingredienteDTOToIngrediente(ingredienteDTO);
                    return ingredienteRepository.save(ingrediente); // Persistir ingrediente aquí
                })
                .collect(Collectors.toList());
    }

    // Método para mapear los pasos y asociarlos a la receta
    private List<Paso> mapearPasos(List<PasoDTO> pasosDTO, Receta recetaCreada) {
        return pasosDTO.stream()
                .map(pasoDTO -> {
                    Paso paso = pasoMapper.pasoDTOToPaso(pasoDTO);
                    paso.setReceta(recetaCreada); // Asocia cada paso a la receta

                    // Mapear ingredientes para el paso
                    if (pasoDTO.ingredientes() != null) {
                        paso.setIngredientes(mapearYGuardarIngredientes(pasoDTO.ingredientes()));
                    }
                    return paso;
                })
                .collect(Collectors.toList());
    }

    @Override
    public RecetaDTO obtenerRecetaPorId(UUID id) {
        // Obtener receta por ID y calcular su tiempo total
        Receta receta = obtenerReceta(id);
        calcularTiempoTotalReceta(receta);

        return recetaMapper.recetaToRecetaDTO(receta);
    }

    @Override
    public List<RecetaDTO> obtenerRecetasPorCategoria(UUID categoriaId) {
        // Obtener recetas filtradas por categoría y calcular tiempo total
        List<Receta> recetas = recetaRepository.findByCategoriaId(categoriaId);
        recetas.forEach(this::calcularTiempoTotalReceta);
        return recetas.stream()
                .map(recetaMapper::recetaToRecetaDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void eliminarReceta(UUID id) {
        // Eliminar receta de la base de datos
        recetaRepository.deleteById(id);
    }

    @Override
    @Transactional
    public RecetaDTO actualizarPasos(UUID id, List<PasoDTO> pasosDTO) {
        // Obtener la receta existente
        Receta receta = obtenerReceta(id);

        // Actualiza los pasos de la receta
        List<Paso> pasosActualizados = pasosDTO.stream()
                .map(pasoMapper::pasoDTOToPaso)
                .collect(Collectors.toList());

        // Eliminar pasos que ya no están en la lista
        receta.getPasos().removeIf(p -> !pasosActualizados.stream()
                .anyMatch(np -> np.getId() != null && np.getId().equals(p.getId())));

        // Asignar la receta actual a los pasos
        pasosActualizados.forEach(p -> p.setReceta(receta));

        // Agregar pasos nuevos
        pasosActualizados.forEach(p -> {
            if (p.getId() == null) {
                receta.getPasos().add(p);
            }
        });

        return recetaMapper.recetaToRecetaDTO(receta);
    }

    @Override
    @Transactional
    public PasoDTO actualizarPaso(UUID recetaId, UUID pasoId, PasoDTO pasoDTO) {
        // Obtener la receta y el paso existente
        Receta receta = obtenerReceta(recetaId);
        Paso pasoExistente = receta.getPasos().stream()
                .filter(p -> p.getId().equals(pasoId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Paso no encontrado"));

        // Solo actualizar los campos permitidos en el paso
        if (pasoDTO.descripcion() != null) {
            pasoExistente.setDescripcion(pasoDTO.descripcion());
        }
        if (pasoDTO.tiempo() != null) {
            pasoExistente.setTiempo(pasoDTO.tiempo());
        }
        if (pasoDTO.esOpcional() != null) {
            pasoExistente.setEsOpcional(pasoDTO.esOpcional());
        }

        // Actualizar ingredientes
        if (pasoDTO.ingredientes() != null) {
            List<Ingrediente> ingredientesPersistidos = pasoDTO.ingredientes().stream()
                    .map(ingredienteDTO -> {
                        if (ingredienteDTO.id() != null) {
                            // Si el ingrediente ya existe, solo lo recuperamos
                            return ingredienteRepository.findById(ingredienteDTO.id())
                                    .orElseThrow(() -> new RuntimeException("Ingrediente no encontrado"));
                        } else {
                            // Si es un nuevo ingrediente, lo guardamos
                            Ingrediente ingrediente = ingredienteMapper.ingredienteDTOToIngrediente(ingredienteDTO);
                            return ingredienteRepository.save(ingrediente);
                        }
                    })
                    .collect(Collectors.toList());
            pasoExistente.setIngredientes(ingredientesPersistidos);
        }

        return pasoMapper.pasoToPasoDTO(pasoExistente);
    }

    @Override
    public RecetaDTO actualizarReceta(UUID id, RecetaDTO recetaDTO) {
        // Obtener la receta existente y actualizarla con los datos del DTO
        Receta recetaExistente = obtenerReceta(id);

        recetaMapper.actualizarRecetaDesdeDTO(recetaDTO, recetaExistente);
        return recetaMapper.recetaToRecetaDTO(recetaExistente);
    }

    @Override
    public List<IngredienteDTO> obtenerIngredientesPorReceta(UUID id) {
        // Obtener los ingredientes asociados a una receta
        Receta receta = obtenerReceta(id);
        return receta.getIngredientes().stream()
                .map(ingredienteMapper::ingredienteToIngredienteDTO)
                .collect(Collectors.toList());
    }

    // Método privado para obtener una receta por ID o lanzar una excepción si no se encuentra
    private Receta obtenerReceta(UUID id) {
        return recetaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receta no encontrada"));
    }

    // Método privado para calcular el tiempo total de la receta
    private void calcularTiempoTotalReceta(Receta receta) {
        int tiempoTotal = receta.getPasos().stream()
                .mapToInt(Paso::getTiempo)
                .sum();
        receta.setTiempoTotal(tiempoTotal); // Asignar el tiempo total calculado a la receta
    }
}
