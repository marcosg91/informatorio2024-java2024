package com.info.info_gestionderecetas_app.repository.receta;

import com.info.info_gestionderecetas_app.domain.Receta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RecetaRepository extends JpaRepository <Receta, UUID> {
}
