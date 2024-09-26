package com.info.app.proyectoapp.dto.user;

import com.info.app.proyectoapp.domain.enums.RolEnum;

import java.util.UUID;

public record UsuarioDto(
        String nombre,
        String email,
        RolEnum rol,
        UUID idProyecto){}
