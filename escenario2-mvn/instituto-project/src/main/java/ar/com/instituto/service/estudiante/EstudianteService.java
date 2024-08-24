package ar.com.instituto.service.estudiante;

import ar.com.instituto.domain.Estudiante;

import java.util.UUID;

public interface EstudianteService {
    Estudiante registrarEstudiante();
    void inscribirEstudiante(UUID idCurso);

}
