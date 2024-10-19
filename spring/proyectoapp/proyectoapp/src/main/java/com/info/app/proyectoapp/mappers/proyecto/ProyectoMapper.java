package com.info.app.proyectoapp.mappers.proyecto;

import com.info.app.proyectoapp.domain.Proyecto;
import com.info.app.proyectoapp.dto.proyecto.ProyectoCreateDto;
import com.info.app.proyectoapp.dto.proyecto.ProyectoCreatedDto;
import com.info.app.proyectoapp.dto.proyecto.ProyectoDto;
import com.info.app.proyectoapp.dto.proyecto.ProyectoUpdatedDto;

public interface ProyectoMapper {

    ProyectoUpdatedDto proyectoToProyectoUpdatedDto(Proyecto proyecto);

    Proyecto proyectoCreateDtoToProyecto(ProyectoCreateDto proyectoCreateDto);

    ProyectoCreatedDto proyectoToProyectoCreatedDto(Proyecto proyecto);

    ProyectoDto proyectoToProyectoDto(Proyecto proyecto);
}