package com.info.info_gestionderecetas_app.controller.paso;

import com.info.info_gestionderecetas_app.dto.paso.PasoDTO;
import com.info.info_gestionderecetas_app.service.paso.PasoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/recetas")
public class PasoController {

    @Autowired // Inyección de dependencias para utilizar los métodos de PasoService
    private PasoService pasoService;

    // Endpoint para crear un nuevo paso en una receta
    @PostMapping("/{recetaId}/pasos")
    public PasoDTO crearPaso(@PathVariable UUID recetaId, @RequestBody PasoDTO pasoDTO) {
        // Llama al servicio para crear un nuevo paso dentro de una receta específica
        return pasoService.crearPaso(recetaId, pasoDTO);
    }

    // Endpoint para eliminar un paso por su id dentro de una receta
    @DeleteMapping("/{recetaId}/pasos/{pasoId}")
    public ResponseEntity<Void> eliminarPaso(@PathVariable UUID recetaId, @PathVariable UUID pasoId) {
        // Llama al servicio para eliminar un paso de una receta específica
        pasoService.eliminarPaso(recetaId, pasoId);
        // Retorna un 204
        return ResponseEntity.noContent().build();
    }
}
