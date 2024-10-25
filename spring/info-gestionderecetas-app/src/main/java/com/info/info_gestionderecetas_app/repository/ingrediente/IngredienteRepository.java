package com.info.info_gestionderecetas_app.repository.ingrediente;

import com.info.info_gestionderecetas_app.domain.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente, UUID> {
    List<Ingrediente> findByPasos_Id(UUID pasoId);
    Optional<Ingrediente> findByNombre(String nombre);
}
