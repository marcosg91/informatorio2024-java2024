package ar.com.instituto.service.menu.impl;

import ar.com.instituto.bd.BdCursos;
import ar.com.instituto.service.archivos.ArchivosCursosService;
import ar.com.instituto.service.curso.CursoService;
import  ar.com.instituto.service.menu.MenuService;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.UUID;

public class MenuServiceImpl implements MenuService {

    private CursoService cursoService;
    private ArchivosCursosService archivosCursosService;

    public MenuServiceImpl(CursoService cursoService, ArchivosCursosService archivosCursosService) {
        this.cursoService = cursoService;
        this.archivosCursosService = archivosCursosService;
    }

    @Override
    public void mostrarMenu(Scanner scanner) {
        int opcion;
        do {
            System.out.println("Ingrese opcion: \n"); // \n = salto de linea
            System.out.println("1- Crear curso");
            System.out.println("2- Registrar estudiante nuevo");
            System.out.println("3- Registrar a un curso a partir de un estudiante existente");
            System.out.println("4- Listar cursos");
            System.out.println("5- Listar cursos y estudiantes");
            System.out.println("6- Exportar cursos");
            System.out.println("7- Salir");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:// funcionalidad crear curso
                    cursoService.crearCurso();
                    break;
                case 2:// funcionalidad registrar estudiante nuevo
                    System.out.println("Ingrese ID del curso");
                    String idCurso = scanner.nextLine();
                    scanner.nextLine();
                    cursoService.inscribirEstudiante(UUID.fromString(idCurso));
                    break;
                case 3:// funcionalidad registrar estudiante existente
                    System.out.println("Ingrese ID del curso");
                    idCurso = scanner.nextLine();
                    System.out.println("Ingrese DNI del estudiante");
                    Long dniEstudiante = scanner.nextLong();
                    scanner.nextLine();
                    try {
                        cursoService.inscribirEstudianteACurso(UUID.fromString(idCurso), dniEstudiante);
                    } catch (
                            NoSuchElementException e) {//atrapo la excepcion que se envia en el metodo en Institucion.java
                        System.out.println(e.getMessage());//muestro el mensaje que puse en el metodo
                    }
                    break;
                case 4:// funcionalidad listar curso
                    cursoService.listarCursos();
                    break;
                case 5://funcionalidad listar cursos y estudiantes
                    break;
                case 6://exportar archivo
                    archivosCursosService.exportarCursosCsv(BdCursos.getCursoList());
                    break;
                case 7:
                    System.out.println("\n Saliendo...");
                    break;
                default:
                    break;
            }
        } while (opcion != 7); // != = distinto de
    }
}


