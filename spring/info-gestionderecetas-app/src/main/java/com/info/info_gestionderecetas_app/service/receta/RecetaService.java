package com.info.info_gestionderecetas_app.service.receta;

import com.info.info_gestionderecetas_app.dto.ingrediente.IngredienteDTO;
import com.info.info_gestionderecetas_app.dto.receta.RecetaDTO;
import com.info.info_gestionderecetas_app.dto.paso.PasoDTO;

import java.util.List;
import java.util.UUID;

public interface RecetaService {
    RecetaDTO crearReceta(RecetaDTO receta);
    RecetaDTO obtenerRecetaPorId(UUID id);
    List<RecetaDTO> obtenerRecetasPorCategoria(UUID categoriaId);
    void eliminarReceta(UUID id);
    RecetaDTO actualizarPasos(UUID id, List<PasoDTO> pasos);
    PasoDTO actualizarPaso(UUID recetaId, UUID pasoId, PasoDTO pasoDTO);
    RecetaDTO actualizarReceta(UUID id, RecetaDTO recetaDTO);
    List<IngredienteDTO> obtenerIngredientesPorReceta(UUID id);
}
