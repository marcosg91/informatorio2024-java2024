package escenario2;

import escenario2.dominio.Institucion;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.UUID;

public class App {
    public static void main(String[] args) {
        Institucion institucion = new Institucion();
        int opcion;
        Scanner scanner = new Scanner((System.in));
        do {
            System.out.println("Ingrese opcion: \n"); // \n = salto de linea
            System.out.println("1- Crear curso");
            System.out.println("2- Registrar estudiante nuevo");
            System.out.println("3- Registrar a un curso a partir de un estudiante existente");
            System.out.println("4- Listar cursos");
            System.out.println("5- Listar cursos y estudiantes");
            System.out.println("6- Salir");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:// funcionalidad crear curso
                    institucion.crearCurso();
                    break;
                case 2:// funcionalidad registrar estudiante nuevo
                    System.out.println("Ingrese ID del curso");
                    String idCurso = scanner.nextLine();
                    scanner.nextLine();
                    institucion.inscribirEstudiante(UUID.fromString(idCurso));
                    break;
                case 3:// funcionalidad registrar estudiante existente
                    System.out.println("Ingrese ID del curso");
                    idCurso = scanner.nextLine();
                    System.out.println("Ingrese DNI del estudiante");
                    Long dniEstudiante = scanner.nextLong();
                    scanner.nextLine();
                    try {
                        institucion.inscribirEstudianteACurso(UUID.fromString(idCurso), dniEstudiante);
                    } catch (
                            NoSuchElementException e) {//atrapo la excepcion que se envia en el metodo en Institucion.java
                        System.out.println(e.getMessage());//muestro el mensaje que puse en el metodo
                    }
                    break;
                case 4:// funcionalidad listar curso
                    institucion.listarCursos();
                    break;
                case 5://funcionalidad listar cursos y estudiantes
                    break;
                case 6:
                    System.out.println("\n Saliendo...");
                    break;
                default:
                    break;
            }

        } while (opcion != 6); // != = distinto de

        scanner.close();
    }
}
