package com.info.app.proyectoapp.dto.proyecto;

import com.info.app.proyectoapp.dto.user.UsuarioDto;

import java.time.LocalDate;

public record ProyectoUpdatedDto(String nombre, LocalDate fechaInicio, LocalDate fechaFin, UsuarioDto lider) {
}