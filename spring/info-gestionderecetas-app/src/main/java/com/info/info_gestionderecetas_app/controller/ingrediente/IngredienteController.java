package com.info.info_gestionderecetas_app.controller.ingrediente;

import com.info.info_gestionderecetas_app.dto.ingrediente.IngredienteDTO;
import com.info.info_gestionderecetas_app.service.ingrediente.IngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/ingredientes")
public class IngredienteController {

    @Autowired
    private IngredienteService ingredienteService; // Inyección del servicio IngredienteService para manejar la lógica de negocio

    // Obtener todos los ingredientes
    @GetMapping
    public ResponseEntity<List<IngredienteDTO>> obtenerIngredientes() {
        // Llama al servicio para obtener la lista de ingredientes
        List<IngredienteDTO> ingredientes = ingredienteService.obtenerTodosLosIngredientes();
        // Retorna la lista de ingredientes
        return ResponseEntity.ok(ingredientes);
    }

    // Crear un nuevo ingrediente
    @PostMapping
    public ResponseEntity<IngredienteDTO> crearIngrediente(@RequestBody IngredienteDTO ingredienteDTO) {
        // Llama al servicio para crear un nuevo ingrediente
        IngredienteDTO nuevoIngrediente = ingredienteService.crearIngrediente(ingredienteDTO);
        // Retorna el ingrediente creado
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoIngrediente);
    }

    // Obtener ingredientes por paso
    @GetMapping("/pasos/{pasoId}")
    public ResponseEntity<List<IngredienteDTO>> obtenerIngredientesPorPaso(@PathVariable UUID pasoId) {
        // Llama al servicio para obtener ingredientes asociados a un paso específico
        List<IngredienteDTO> ingredientes = ingredienteService.obtenerIngredientesPorPaso(pasoId);
        // Retorna la lista de ingredientes
        return ResponseEntity.ok(ingredientes);
    }

    // Crear ingrediente en un paso específico
    @PostMapping("/pasos/{pasoId}")
    public ResponseEntity<Object> crearIngredienteEnPaso(
            @PathVariable UUID pasoId,
            @RequestBody IngredienteDTO ingredienteDTO) {
        // Verifica si el paso existe antes de crear el ingrediente
        if (!ingredienteService.pasoExiste(pasoId)) {
            // Si el paso no existe, retorna un código de error
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("No se puede crear un ingrediente porque el paso no existe.");
        }

        // Si el paso existe, crea el ingrediente en ese paso
        IngredienteDTO nuevoIngrediente = ingredienteService.crearIngredienteEnPaso(pasoId, ingredienteDTO);
        // Retorna el ingrediente creado
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoIngrediente);
    }

    // Eliminar ingrediente por ID
    @DeleteMapping("/{ingredienteId}")
    public ResponseEntity<Void> eliminarIngrediente(@PathVariable UUID ingredienteId) {
        // Llama al servicio para eliminar el ingrediente por su id
        ingredienteService.eliminarIngrediente(ingredienteId);
        // Retorna una respuesta vacía - 204 (No Content)
        return ResponseEntity.noContent().build();
    }

    // Obtener todos los ingredientes de una receta
    @GetMapping("/recetas/{recetaId}")
    public ResponseEntity<List<IngredienteDTO>> obtenerIngredientesPorReceta(@PathVariable UUID recetaId) {
        // Llama al servicio para obtener los ingredientes asociados a una receta específica
        List<IngredienteDTO> ingredientes = ingredienteService.obtenerIngredientesPorReceta(recetaId);
        // Retorna la lista de ingredientes
        return ResponseEntity.ok(ingredientes);
    }
}
