package com.info.info_gestionderecetas_app.service.categoria;

import com.info.info_gestionderecetas_app.domain.Categoria;
import com.info.info_gestionderecetas_app.repository.categoria.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoriaServiceImpl implements CategoriaService{

    private CategoriaRepository categoriaRepository;

    @Override
    public Categoria getCategoriaById(UUID uuid) {
        //hacemos la busqueda en la base de datos

        Optional<Categoria> optionalCategoria = categoriaRepository.findById(uuid);
        if (optionalCategoria.isPresent()) {
            return optionalCategoria.get();
        }else {
            throw new NoSuchElementException("Categoria no encontrada");
        }
    }
}
