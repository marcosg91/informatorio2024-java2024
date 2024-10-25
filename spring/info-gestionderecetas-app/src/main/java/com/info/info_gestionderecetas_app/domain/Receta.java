package com.info.info_gestionderecetas_app.domain;

import com.info.info_gestionderecetas_app.domain.enums.Dificultad;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Receta {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String nombre;

    private String descripcion;

    @ManyToOne
    private Categoria categoria;

    @Enumerated(EnumType.STRING)
    private Dificultad dificultad;

    @OneToMany(mappedBy = "receta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Paso> pasos = new ArrayList<>();

    @Setter
    @Getter
    @OneToMany(mappedBy = "receta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ingrediente> ingredientes = new ArrayList<>();

    private Integer tiempoTotal;

}
