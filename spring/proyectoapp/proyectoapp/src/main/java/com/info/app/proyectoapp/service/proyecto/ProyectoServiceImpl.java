package com.info.app.proyectoapp.service.proyecto;

import com.info.app.proyectoapp.domain.Proyecto;
import com.info.app.proyectoapp.dto.proyecto.ProyectoCreateDto;
import com.info.app.proyectoapp.dto.proyecto.ProyectoCreatedDto;
import com.info.app.proyectoapp.dto.proyecto.ProyectoDto;
import com.info.app.proyectoapp.dto.proyecto.ProyectoUpdatedDto;
import com.info.app.proyectoapp.mappers.proyecto.ProyectoMapper;
import com.info.app.proyectoapp.repository.proyecto.ProyectoRepository;
import com.info.app.proyectoapp.repository.usuario.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@AllArgsConstructor
public class ProyectoServiceImpl implements ProyectoService{

    private final UsuarioRepository usuarioRepository;
    private ProyectoRepository proyectoRepository;

    private ProyectoMapper proyectoMapper;

    @Override
    public Proyecto getProyectoById(UUID uuid) {
        Optional<Proyecto> optionalProyecto = proyectoRepository.findById(uuid);

        if (optionalProyecto.isPresent()) {
            return optionalProyecto.get();
        }else {
            throw new NoSuchElementException("Proyecto no encontrado");
        }
    }

    @Override
    public Optional<ProyectoDto> getProyectoDtoById(UUID uuid) {
        Optional<Proyecto> optionalProyecto = proyectoRepository.findById(uuid);

        if(optionalProyecto.isPresent()) {
            return Optional.of(
                    proyectoMapper.proyectoToProyectoDto( optionalProyecto.get() )
            );
        }else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<ProyectoUpdatedDto> closeProyecto(UUID uuid) {
        Optional<Proyecto> proyecto = proyectoRepository.findById(uuid);

        if ( proyecto.isPresent() ){
            var proyectoEncontrado = proyecto.get();
            proyectoEncontrado.setFechaFin( LocalDate.now() );
            var proyectoUpdated = proyectoRepository.save( proyectoEncontrado );
            return Optional.of( proyectoMapper.proyectoToProyectoUpdatedDto( proyectoUpdated )  );
        }

        return Optional.empty();
    }

    @Override
    public Optional<ProyectoCreatedDto> createProyecto(ProyectoCreateDto proyectoCreateDto) {

        Proyecto newProject = proyectoMapper.proyectoCreateDtoToProyecto( proyectoCreateDto );

        if ( !proyectoCreateDto.colaboradoresId().isEmpty() ){

            //Controlar que todos los usuarios existan en la bd, sino excepcion


            //Controlar que el usuario no tenga un proyecto ya asignado

        }

        if ( proyectoCreateDto.liderId() != null ){

            //Controlar que el usuario exista en la bd

            //Controlar que el usuario no sea lider en otro proyecto.

        }

        return Optional.of(
                proyectoMapper.proyectoToProyectoCreatedDto( proyectoRepository.save( newProject ) )
        );
    }

    @Override
    public List<ProyectoDto> getAllProyectos() {

        return proyectoRepository.findAll().stream()
                .map( proyecto -> proyectoMapper.proyectoToProyectoDto(proyecto) )
                .toList();
    }
}