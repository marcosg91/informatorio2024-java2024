package com.info.info_gestionderecetas_app.service.receta;

import com.info.info_gestionderecetas_app.domain.Categoria;
import com.info.info_gestionderecetas_app.domain.Receta;
import com.info.info_gestionderecetas_app.dto.receta.RecetaDTO;
import com.info.info_gestionderecetas_app.mapper.receta.RecetaMapper;
import com.info.info_gestionderecetas_app.repository.categoria.CategoriaRepository;
import com.info.info_gestionderecetas_app.repository.receta.RecetaRepository;
import com.info.info_gestionderecetas_app.service.categoria.CategoriaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RecetaServiceImpl implements RecetaService {

    private RecetaMapper recetaMapper;

    private CategoriaService categoriaService;

    private CategoriaRepository categoriaRepository;

    private RecetaRepository recetaRepository;

    // 1. Crear una nueva receta
    @Override
    public RecetaDTO crearReceta(RecetaDTO receta) {

        Receta recetaCreada = recetaMapper.recetaDTOToReceta( receta);

        Categoria categoria = categoriaService.getCategoriaById( receta.idCategoria() );

        recetaCreada.setCategoria(categoria);

        categoriaRepository.save( categoria );
        return recetaMapper.recetaToRecetaDTO( recetaRepository.save( recetaCreada ) ) ;

    }
}

