package com.info.info_gestionderecetas_app.mapper.paso;

import com.info.info_gestionderecetas_app.domain.Paso;
import com.info.info_gestionderecetas_app.dto.paso.PasoDTO;

public interface PasoMapper {
    PasoDTO pasoToPasoDTO(Paso paso);
    Paso pasoDTOToPaso(PasoDTO pasoDTO);
    void actualizarPasoDesdeDTO(PasoDTO pasoDTO, Paso paso);
}
