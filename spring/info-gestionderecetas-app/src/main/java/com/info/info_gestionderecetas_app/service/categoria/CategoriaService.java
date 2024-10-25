package com.info.info_gestionderecetas_app.service.categoria;

import com.info.info_gestionderecetas_app.dto.categoria.CategoriaDTO;

import java.util.List;
import java.util.UUID;

public interface CategoriaService {
    CategoriaDTO crearCategoria(CategoriaDTO categoriaDTO);
    CategoriaDTO obtenerCategoriaPorId(UUID id);
    List<CategoriaDTO> obtenerTodasLasCategorias();
}
