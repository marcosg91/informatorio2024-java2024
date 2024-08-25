package ar.com.instituto.service.curso.impl;

import ar.com.instituto.domain.Curso;
import ar.com.instituto.domain.Estudiante;
import ar.com.instituto.enumeration.ComplejidadEnum;
import ar.com.instituto.service.curso.CursoService;
import ar.com.instituto.service.estudiante.EstudianteService;
import ar.com.instituto.service.institucion.InstitucionService;

import java.util.*;

//las clases de implementacion (interfaz) albergan las funcionalidades

public class CursoServiceImpl implements CursoService {
    private EstudianteService estudianteService;
    private InstitucionService institucionService;

    public CursoServiceImpl(EstudianteService estudianteService, InstitucionService institucionService) {
        this.estudianteService = estudianteService;
        this.institucionService = institucionService;
    }

    @Override
    public void inscribirEstudiante(UUID idCurso) { //para relacionar un estudiante con un curso
        Estudiante estudiante = estudianteService.registrarEstudiante();

        boolean existeCurso = Boolean.FALSE;

        for (Curso curso : institucionService.getCursos()) {
            if (curso.getId().equals(idCurso)) {
                // encontramos el curso
                estudiante.getCursos().add(curso);
                curso.getEstudiantes().put(estudiante.getDni(), estudiante);
                existeCurso = Boolean.TRUE;

                break;

            }
        }
    }

    @Override
    public Curso crearCurso() {
        Curso nuevoCurso = new Curso();
        Scanner sc = new Scanner(System.in);

        nuevoCurso.setId(UUID.randomUUID());
        System.out.println("Ingrese nombre del curso: ");
        String nombreCurso = sc.nextLine();
        sc.nextLine();
        nuevoCurso.setNombre(nombreCurso);

        System.out.println("Ingrese la complejidad del curso: ");
        System.out.println("1- ALTA");
        System.out.println("2- MEDIA");
        System.out.println("3- BAJA");
        int complejidad = sc.nextInt();
        sc.nextLine();

        /*

        switch (complejidad){
            case 1:
                nuevoCurso.setComplejidad(ComplejidadEnum.ALTA);
                break;
            case 2:
                nuevoCurso.setComplejidad(ComplejidadEnum.MEDIA);
                break;
            case 3:
                nuevoCurso.setComplejidad(ComplejidadEnum.BAJA);
                break;
            default: // en caso de no ingresar 1 2 o 3
                nuevoCurso.setComplejidad(null);
        }*/
        // JAVA 17 - SWITCH    (nueva version)
        nuevoCurso.setComplejidad(switch (complejidad) {
            case 1 -> ComplejidadEnum.ALTA;
            case 2 -> ComplejidadEnum.MEDIA;
            case 3 -> ComplejidadEnum.BAJA;
            default -> null;
        });
        System.out.println("Ingrese cantidad de horas del curso: ");
        int horas = sc.nextInt();
        sc.nextLine();
        nuevoCurso.setCantidadHoras(horas);

        institucionService.getCursos().add(nuevoCurso);
        System.out.println("Curso creado satisfactoriamente");

        return nuevoCurso;
    }

    @Override
    public void inscribirEstudianteACurso(UUID idCurso, Long dni) {
        Estudiante estudiante = null;
        boolean existeElEstudiante = Boolean.FALSE;
        boolean esCursoEncontrado = Boolean.FALSE;

        for (Curso curso : institucionService.getCursos()) {
            if (curso.getEstudiantes().containsKey(dni)) {
                estudiante = curso.getEstudiantes().get(dni);
                existeElEstudiante = Boolean.TRUE;
                break;
            }
        }
        //excepcion
        if (!existeElEstudiante) {
            throw new NoSuchElementException("No existe el estudiante");
        }
        for (Curso curso : institucionService.getCursos()) {
            if (curso.getId().equals(idCurso)) {
                estudiante.getCursos().add(curso);
                curso.getEstudiantes().put(estudiante.getDni(), estudiante);
                esCursoEncontrado = Boolean.TRUE;
                break;
            }
        }
        if (!esCursoEncontrado) {
            throw new NoSuchElementException("No existe el curso");
        } else {
            System.out.println("Estudiante asignado al curso");
        }
    }

    @Override
    public void listarCursos() {
        System.out.println("Lista de cursos disponibles");
        for (Curso curso : institucionService.getCursos()) {
            System.out.println(curso.toString());
        }
    }

    @Override
    public void listarEstudiantesYCursos() {
        Set<Estudiante> listasEstudiantesSinRepetir = new HashSet<>(); //para evi

        for (Curso curso : institucionService.getCursos()) {
            listasEstudiantesSinRepetir.addAll(curso.getEstudiantes().values());
        }

        ArrayList<Estudiante> listaEstudiantes = new ArrayList<>(listasEstudiantesSinRepetir);
        for (Estudiante estudiante : listaEstudiantes) {
            System.out.println(estudiante.toString());//muestra cada uno de los estudiantes

            for (Curso curso : estudiante.getCursos()) {
                System.out.println(curso.toString());//muestra el curso al/ a los que esta inscripto un estudiante
            }
        }
    }
}


