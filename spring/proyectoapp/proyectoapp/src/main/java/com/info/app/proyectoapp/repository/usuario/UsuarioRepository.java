package com.info.app.proyectoapp.repository.usuario;

import com.info.app.proyectoapp.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
}
