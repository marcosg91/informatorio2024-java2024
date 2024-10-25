package com.info.info_gestionderecetas_app.service.categoria;

import com.info.info_gestionderecetas_app.domain.Categoria;
import com.info.info_gestionderecetas_app.dto.categoria.CategoriaDTO;
import com.info.info_gestionderecetas_app.repository.categoria.CategoriaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor // Genera un constructor con todos los argumentos necesarios usando Lombok
public class CategoriaServiceImpl implements CategoriaService {

    // Inyección de dependencia para interactuar con el repositorio de Categoría
    private final CategoriaRepository categoriaRepository;


    @Override
    public CategoriaDTO crearCategoria(CategoriaDTO categoriaDTO) {
        // Crear una nueva entidad de Categoría
        Categoria categoria = new Categoria();
        categoria.setId(UUID.randomUUID()); // Asignar un ID único a la categoría
        categoria.setNombre(categoriaDTO.nombre()); // Asignar el nombre desde el DTO

        // Guardar la categoría en el repositorio
        Categoria categoriaGuardada = categoriaRepository.save(categoria);

        // Retornar el DTO con los datos de la categoría guardada
        return new CategoriaDTO(categoriaGuardada.getId(), categoriaGuardada.getNombre());
    }


    @Override
    public CategoriaDTO obtenerCategoriaPorId(UUID id) {
        // Buscar la categoría en el repositorio, lanzar excepción si no se encuentra
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        // Retornar un DTO con los datos de la categoría encontrada
        return new CategoriaDTO(categoria.getId(), categoria.getNombre());
    }


    @Override
    public List<CategoriaDTO> obtenerTodasLasCategorias() {
        // Obtener todas las categorías del repositorio, convertir cada entidad a DTO
        return categoriaRepository.findAll().stream()
                .map(categoria -> new CategoriaDTO(categoria.getId(), categoria.getNombre())) // Mapear entidad a DTO
                .collect(Collectors.toList()); // Recolectar los DTOs en una lista
    }
}
