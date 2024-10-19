package com.info.info_gestionderecetas_app.repository.categoria;

import com.info.info_gestionderecetas_app.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoriaRepository extends JpaRepository <Categoria, UUID> {
}
