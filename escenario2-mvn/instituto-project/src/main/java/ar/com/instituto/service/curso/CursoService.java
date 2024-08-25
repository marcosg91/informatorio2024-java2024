package ar.com.instituto.service.curso;

import ar.com.instituto.domain.Curso;

import java.util.UUID;

//las clases de servicio se encargan de la parte logica de una entidad

public interface CursoService {
    void inscribirEstudiante(UUID idCurso);

    Curso crearCurso();

    void inscribirEstudianteACurso(UUID idCurso, Long dni);

    void listarCursos();

    void listarEstudiantesYCursos();
}
