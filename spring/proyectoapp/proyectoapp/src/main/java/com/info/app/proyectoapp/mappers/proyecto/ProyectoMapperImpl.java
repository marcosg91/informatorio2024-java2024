package com.info.app.proyectoapp.mappers.proyecto;

import com.info.app.proyectoapp.domain.Proyecto;
import com.info.app.proyectoapp.dto.proyecto.ProyectoCreateDto;
import com.info.app.proyectoapp.dto.proyecto.ProyectoCreatedDto;
import com.info.app.proyectoapp.dto.proyecto.ProyectoDto;
import com.info.app.proyectoapp.dto.proyecto.ProyectoUpdatedDto;
import com.info.app.proyectoapp.mappers.user.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
@AllArgsConstructor
public class ProyectoMapperImpl implements  ProyectoMapper{

    private UserMapper userMapper;

    @Override
    public ProyectoUpdatedDto proyectoToProyectoUpdatedDto(Proyecto proyecto) {

        return new ProyectoUpdatedDto(
                proyecto.getNombre(),
                proyecto.getFechaInicio(),
                proyecto.getFechaFin(),
                userMapper.usuarioToUsuarioDto(proyecto.getLider())
        );
    }

    @Override
    public Proyecto proyectoCreateDtoToProyecto(ProyectoCreateDto proyectoCreateDto) {

        Proyecto proyecto = new Proyecto();
        proyecto.setId( UUID.randomUUID() );
        proyecto.setFechaInicio( LocalDate.now() );
        proyecto.setNombre( proyectoCreateDto.nombre() );

        return proyecto;
    }

    @Override
    public ProyectoCreatedDto proyectoToProyectoCreatedDto(Proyecto proyecto) {

        return new ProyectoCreatedDto(
                proyecto.getId(),
                proyecto.getNombre(),
                proyecto.getFechaInicio(),
                userMapper.usuarioToUsuarioDto( proyecto.getLider() ),
                proyecto.getColaboradores()
                        .stream()
                        .map( colaborador -> userMapper.usuarioToUsuarioDto( colaborador ) )
                        .toList()
        );
    }

    @Override
    public ProyectoDto proyectoToProyectoDto(Proyecto proyecto) {

        return new ProyectoDto(
                proyecto.getId(),
                proyecto.getNombre(),
                proyecto.getFechaInicio(),
                proyecto.getFechaFin(),
                userMapper.usuarioToUsuarioDto( proyecto.getLider() ),
                proyecto.getColaboradores()
                        .stream()
                        .map( colaborador -> userMapper.usuarioToUsuarioDto( colaborador ) )
                        .toList()
        );
    }
}