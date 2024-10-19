package com.info.app.proyectoapp.domain;

import com.info.app.proyectoapp.domain.enums.RolEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
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

    private LocalDate fechaFin;

    @OneToOne
    @JoinColumn(name = "lider_id")
    private Usuario lider;

    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Usuario> colaboradores = new ArrayList<>();

    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER )
    private List<Tarea> tarea = new ArrayList<>();

    public void setUsuarioByRol(Usuario usuario){

        if ( RolEnum.LIDER.equals( usuario.getRol() ) ){
            this.setLider( usuario );
        }else {
            this.getColaboradores().add( usuario );
        }

    }

}