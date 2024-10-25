package com.info.info_gestionderecetas_app.mapper.categoria;

import com.info.info_gestionderecetas_app.domain.Categoria;
import com.info.info_gestionderecetas_app.dto.categoria.CategoriaDTO;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapperImpl implements CategoriaMapper {

    // Convierte un CategoriaDTO en una entidad Categoria
    @Override
    public Categoria categoriaDTOToCategoria(CategoriaDTO categoriaDTO) {
        // Crea una nueva instancia de Categoria
        Categoria categoria = new Categoria();

        // Asigna el nombre del CategoriaDTO a la entidad Categoria
        categoria.setNombre(categoriaDTO.nombre());

        // Retorna la entidad Categoria con el nombre asignado
        return categoria;
    }

    // Convierte una entidad Categoria en un CategoriaDTO
    @Override
    public CategoriaDTO categoriaToCategoriaDTO(Categoria categoria) {
        // Retorna un nuevo CategoriaDTO usando los valores de la entidad Categoria
        return new CategoriaDTO(
                categoria.getId(),
                categoria.getNombre()
        );
    }
}
