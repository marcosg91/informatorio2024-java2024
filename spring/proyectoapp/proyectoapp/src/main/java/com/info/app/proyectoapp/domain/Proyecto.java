package com.info.app.proyectoapp.domain;

import com.info.app.proyectoapp.domain.enums.RolEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Proyecto {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private LocalDate fechaInicio;

    @Column(nullable = false)
    private LocalDate fechaFin;

    //relaciones:

    @OneToOne
    @JoinColumn(name = "lider_id", nullable = false)
    private Usuario lider;

    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Usuario> colaboradores;

    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Tarea> tareas;

    public void setUsuarioByRol(Usuario usuario){
        if (RolEnum.LIDER.equals( usuario.getRol() ) ) {
            this.setLider(usuario);

        }else{
            this.getColaboradores().add(usuario);
        }

    }

}
