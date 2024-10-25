package com.info.info_gestionderecetas_app.repository.paso;

import com.info.info_gestionderecetas_app.domain.Paso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PasoRepository extends JpaRepository<Paso, UUID> {
    List<Paso> findByRecetaId(UUID recetaId);
}
