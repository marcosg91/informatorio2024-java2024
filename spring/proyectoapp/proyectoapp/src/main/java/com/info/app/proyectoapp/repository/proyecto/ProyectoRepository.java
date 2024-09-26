package com.info.app.proyectoapp.repository.proyecto;

import com.info.app.proyectoapp.domain.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProyectoRepository extends JpaRepository<Proyecto, UUID> {
}
