package com.info.app.proyectoapp.controller.proyecto;

import com.info.app.proyectoapp.domain.Proyecto;
import com.info.app.proyectoapp.dto.errores.ErroresDto;
import com.info.app.proyectoapp.dto.proyecto.ProyectoCreateDto;
import com.info.app.proyectoapp.dto.proyecto.ProyectoCreatedDto;
import com.info.app.proyectoapp.dto.proyecto.ProyectoDto;
import com.info.app.proyectoapp.dto.proyecto.ProyectoUpdatedDto;
import com.info.app.proyectoapp.dto.user.UsuarioDto;
import com.info.app.proyectoapp.exceptions.NotFoundException;
import com.info.app.proyectoapp.service.proyecto.ProyectoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class ProyectoController {

    private ProyectoService proyectoService;

    @PostMapping("/api/v1/proyecto")
    public ResponseEntity<?> createProyecto(@RequestBody ProyectoCreateDto proyectoCreateDto) {

        Optional<ProyectoCreatedDto> proyectoCreatedDto = proyectoService.createProyecto( proyectoCreateDto );

        return ResponseEntity
                .status( HttpStatus.CREATED )
                .body( proyectoCreatedDto.get() );
    }

    @PutMapping("/api/v1/proyecto/close/{idProyecto}")
    public ResponseEntity closeProyecto(@PathVariable("idProyecto") UUID idProyecto){
        Optional<ProyectoUpdatedDto> proyectoUpdatedDto = proyectoService.closeProyecto( idProyecto );

        if ( proyectoUpdatedDto.isEmpty() ){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Proyecto no encontrado");
        }

        return ResponseEntity.status( HttpStatus.NO_CONTENT ).build();
    }

    @GetMapping("/api/v1/proyecto")
    public List<ProyectoDto> getAllProyectos(){
        return proyectoService.getAllProyectos();
    }

    @GetMapping("/api/v1/proyecto/{idProyecto}")
    public ResponseEntity<?> getProyectoDtoById(@PathVariable("idProyecto") UUID idProyecto){

        Optional<ProyectoDto> proyectoDto = proyectoService.getProyectoDtoById(idProyecto);

        if ( proyectoDto.isEmpty() ){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErroresDto(
                            String.format("El proyecto(id=%s) no existe", idProyecto.toString() ),
                            HttpStatus.NOT_FOUND.value(),
                            "/api/v1/proyecto/"+idProyecto.toString(
                            )));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(proyectoDto.get());
    }

}