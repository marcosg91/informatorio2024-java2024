package com.info.info_gestionderecetas_app.controller.receta;

import com.info.info_gestionderecetas_app.dto.ingrediente.IngredienteDTO;
import com.info.info_gestionderecetas_app.dto.paso.PasoDTO;
import com.info.info_gestionderecetas_app.dto.receta.RecetaDTO;
import com.info.info_gestionderecetas_app.service.receta.RecetaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/recetas")
@AllArgsConstructor
public class RecetaController {

    private final RecetaService recetaService; // Servicio para manejar la lógica de negocio relacionada con recetas

    // Endpoint para crear una nueva receta
    @PostMapping
    public ResponseEntity<RecetaDTO> crearReceta(@Valid @RequestBody RecetaDTO recetaDTO) {
        // Llama al servicio para crear una nueva receta con los datos proporcionados
        RecetaDTO nuevaReceta = recetaService.crearReceta(recetaDTO);
        // Retorna la receta creada
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaReceta);
    }

    // Endpoint para obtener una receta por su ID
    @GetMapping("/{id}")
    public ResponseEntity<RecetaDTO> obtenerRecetaPorId(@PathVariable UUID id) {
        // Llama al servicio para obtener una receta por su id
        RecetaDTO recetaDTO = recetaService.obtenerRecetaPorId(id);
        // Retorna la receta con un código HTTP 200 (OK)
        return ResponseEntity.ok(recetaDTO);
    }

    // Endpoint para obtener recetas por categoría
    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<RecetaDTO>> obtenerRecetasPorCategoria(@PathVariable UUID categoriaId) {
        // Llama al servicio para obtener recetas que pertenecen a una categoría específica
        List<RecetaDTO> recetas = recetaService.obtenerRecetasPorCategoria(categoriaId);
        // Retorna la lista de recetas con un código HTTP 200 (OK)
        return ResponseEntity.ok(recetas);
    }

    // Endpoint para eliminar una receta por su id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReceta(@PathVariable UUID id) {
        // Llama al servicio para eliminar una receta por su identificador
        recetaService.eliminarReceta(id);
        // Retorna un 204 si la receta fue eliminada exitosamente
        return ResponseEntity.noContent().build();
    }

    // Endpoint para actualizar los pasos de una receta
    @PutMapping("/{id}/pasos")
    public ResponseEntity<RecetaDTO> actualizarPasos(@PathVariable UUID id, @RequestBody List<PasoDTO> pasos) {
        // Llama al servicio para actualizar los pasos de una receta
        RecetaDTO recetaActualizada = recetaService.actualizarPasos(id, pasos);
        // Retorna la receta con los pasos actualizados
        return ResponseEntity.ok(recetaActualizada);
    }

    // Endpoint para actualizar un paso específico de una receta
    @PutMapping("/{id}/pasos/{pasoId}")
    public ResponseEntity<PasoDTO> actualizarPaso(
            @PathVariable UUID id,
            @PathVariable UUID pasoId,
            @RequestBody PasoDTO pasoDTO) {
        // Llama al servicio para actualizar un paso específico de una receta
        PasoDTO pasoActualizado = recetaService.actualizarPaso(id, pasoId, pasoDTO);
        // Retorna el paso actualizado
        return ResponseEntity.ok(pasoActualizado);
    }

    // Endpoint para actualizar toda la receta
    @PutMapping("/{id}")
    public ResponseEntity<RecetaDTO> actualizarReceta(@PathVariable UUID id, @RequestBody RecetaDTO recetaDTO) {
        // Llama al servicio para actualizar toda la receta
        RecetaDTO recetaActualizada = recetaService.actualizarReceta(id, recetaDTO);
        // Retorna la receta actualizada
        return ResponseEntity.ok(recetaActualizada);
    }

    // Endpoint para obtener todos los ingredientes de una receta
    @GetMapping("/{id}/ingredientes")
    public ResponseEntity<List<IngredienteDTO>> obtenerIngredientesPorReceta(@PathVariable UUID id) {
        // Llama al servicio para obtener todos los ingredientes de una receta
        List<IngredienteDTO> ingredientes = recetaService.obtenerIngredientesPorReceta(id);
        // Retorna la lista de ingredientes
        return ResponseEntity.ok(ingredientes);
    }

    // Manejo de excepciones comunes que puedan ocurrir en el controlador
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> manejarExcepciones(RuntimeException e) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
