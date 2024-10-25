package com.info.info_gestionderecetas_app.dto.paso;

import com.info.info_gestionderecetas_app.dto.ingrediente.IngredienteDTO;

import java.util.List;
import java.util.UUID;

public record PasoDTO(
        UUID id,
        String descripcion,
        Integer tiempo,
        Boolean esOpcional,
        List<IngredienteDTO> ingredientes
) {
    // Constructor
    public PasoDTO(UUID id, String descripcion, Integer tiempo, Boolean esOpcional, List<IngredienteDTO> ingredientes) {
        this.id = id;
        this.descripcion = descripcion;
        this.tiempo = tiempo;
        // Si el valor de esOpcional es nulo, se asigna false por defecto
        this.esOpcional = esOpcional != null ? esOpcional : false;
        this.ingredientes = ingredientes;
    }
}
