package com.info.info_gestionderecetas_app.mapper.categoria;

import com.info.info_gestionderecetas_app.domain.Categoria;
import com.info.info_gestionderecetas_app.dto.categoria.CategoriaDTO;

public interface CategoriaMapper {

    Categoria categoriaDTOToCategoria(CategoriaDTO categoriaDTO);

    CategoriaDTO categoriaToCategoriaDTO(Categoria categoria);
}