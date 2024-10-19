package com.info.info_gestionderecetas_app.service.categoria;

import com.info.info_gestionderecetas_app.domain.Categoria;

import java.util.UUID;

public interface CategoriaService {

    Categoria getCategoriaById (UUID uuid);

}
