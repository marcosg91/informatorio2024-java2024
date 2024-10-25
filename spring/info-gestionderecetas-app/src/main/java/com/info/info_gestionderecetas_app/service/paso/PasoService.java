package com.info.info_gestionderecetas_app.service.paso;

import com.info.info_gestionderecetas_app.dto.paso.PasoDTO;

import java.util.List;
import java.util.UUID;

public interface PasoService {
    PasoDTO crearPaso(UUID recetaId, PasoDTO pasoDTO);
    void eliminarPaso(UUID recetaId, UUID pasoId);
}
