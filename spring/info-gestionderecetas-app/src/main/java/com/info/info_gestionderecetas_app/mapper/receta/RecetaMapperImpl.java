package com.info.info_gestionderecetas_app.mapper.receta;

import com.info.info_gestionderecetas_app.domain.Receta;
import com.info.info_gestionderecetas_app.dto.receta.RecetaDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RecetaMapperImpl implements RecetaMapper{

    @Override
    public Receta recetaDTOToReceta(RecetaDTO recetaDTO) {

        Receta recetaCrear = new Receta();
        recetaCrear.setId( UUID.randomUUID() );
        recetaCrear.setNombre( recetaDTO.nombre() );
        recetaCrear.setDescripcion( recetaDTO.descripcion() );
        recetaCrear.setDificultad( recetaDTO.dificultad() );
        recetaCrear.setPasos( recetaDTO.pasos() );

        return recetaCrear;

    }

    @Override
    public RecetaDTO recetaToRecetaDTO(Receta receta) {

        return new RecetaDTO(
                receta.getNombre(),
                receta.getDescripcion(),
                receta.getDificultad(),
                receta.getCategoria().getId(),
                receta.getPasos()
        );
    }
}
