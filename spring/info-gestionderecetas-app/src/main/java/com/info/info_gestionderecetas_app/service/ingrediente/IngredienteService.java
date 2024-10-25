package com.info.info_gestionderecetas_app.service.ingrediente;

import com.info.info_gestionderecetas_app.dto.ingrediente.IngredienteDTO;

import java.util.List;
import java.util.UUID;

public interface IngredienteService {

    IngredienteDTO crearIngrediente(IngredienteDTO ingredienteDTO);

    void eliminarIngrediente(UUID id);

    List<IngredienteDTO> obtenerIngredientesPorReceta(UUID recetaId);

    List<IngredienteDTO> obtenerIngredientesPorPaso(UUID pasoId);

    IngredienteDTO crearIngredienteEnPaso(UUID pasoId, IngredienteDTO ingredienteDTO);

    boolean pasoExiste(UUID pasoId);

    List<IngredienteDTO> obtenerTodosLosIngredientes();
}
