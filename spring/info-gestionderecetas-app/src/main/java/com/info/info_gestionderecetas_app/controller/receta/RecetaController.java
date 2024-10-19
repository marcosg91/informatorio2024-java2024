package com.info.info_gestionderecetas_app.controller.receta;

import com.info.info_gestionderecetas_app.dto.receta.RecetaDTO;
import com.info.info_gestionderecetas_app.service.receta.RecetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/receta")
public class RecetaController {

    private RecetaService recetaService;

    // 1. Crear una nueva receta
    @PostMapping
    public ResponseEntity<?> crearReceta(@RequestBody RecetaDTO receta) {
        RecetaDTO recetaDTO = recetaService.crearReceta(receta);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(recetaDTO);
    }

}

