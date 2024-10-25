package com.info.info_gestionderecetas_app.domain;

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
public class Paso {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String descripcion;

    private Integer tiempo;

    private boolean esOpcional;

    @ManyToOne
    @JoinColumn(name = "receta_id", nullable = false)
    private Receta receta;

    @ManyToMany
    @JoinTable(
            name = "paso_ingrediente",
            joinColumns = @JoinColumn(name = "paso_id"),
            inverseJoinColumns = @JoinColumn(name = "ingrediente_id")
    )
    private List<Ingrediente> ingredientes = new ArrayList<>();

    public Paso(UUID id, String descripcion, int tiempo, boolean esOpcional, Receta receta) {
        this.id = id;
        this.descripcion = descripcion;
        this.tiempo = tiempo;
        this.esOpcional = esOpcional;
        this.receta = receta;
    }

    public void addIngrediente(Ingrediente ingrediente) {
        this.ingredientes.add(ingrediente);
        ingrediente.getPasos().add(this);
    }
}
