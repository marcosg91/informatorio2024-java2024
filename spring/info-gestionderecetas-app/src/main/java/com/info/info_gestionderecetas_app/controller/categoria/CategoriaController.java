package com.info.info_gestionderecetas_app.controller.categoria;

import com.info.info_gestionderecetas_app.dto.categoria.CategoriaDTO;
import com.info.info_gestionderecetas_app.service.categoria.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService; // Inyecta el servicio CategoriaService para manejar la lógica de negocio

    // Endpoint para crear una nueva categoría
    @PostMapping
    public ResponseEntity<CategoriaDTO> crearCategoria(@Valid @RequestBody CategoriaDTO categoriaDTO) {
        // Llama al servicio para crear una nueva categoría con los datos proporcionados
        CategoriaDTO nuevaCategoria = categoriaService.crearCategoria(categoriaDTO);
        // Retorna la nueva categoría creada
        return ResponseEntity.status(201).body(nuevaCategoria);
    }

    // Endpoint para obtener una categoría por su id
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> obtenerCategoria(@PathVariable UUID id) {
        // Llama al servicio para obtener los detalles de una categoría por su id
        CategoriaDTO categoria = categoriaService.obtenerCategoriaPorId(id);
        // Retorna la categoría encontrada
        return ResponseEntity.ok(categoria);
    }

    // Endpoint para obtener todas las categorías
    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> obtenerTodasLasCategorias() {
        // Llama al servicio para obtener una lista de todas las categorías disponibles
        List<CategoriaDTO> categorias = categoriaService.obtenerTodasLasCategorias();
        // Retorna la lista de categorías
        return ResponseEntity.ok(categorias);
    }
}
