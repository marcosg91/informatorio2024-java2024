package ar.com.evento.service.menu.impl;

import ar.com.evento.domain.Chef;
import ar.com.evento.bd.BdEventos;
import ar.com.evento.service.archivos.ArchivosEventosService;
import ar.com.evento.service.chef.ChefService;
import ar.com.evento.service.eventogastronomico.EventoGastronomicoService;
import ar.com.evento.service.menu.MenuService;
import ar.com.evento.service.participante.ParticipanteService;
import ar.com.evento.service.resena.ResenaService;
import ar.com.evento.domain.Participante;

import java.util.Scanner;

public class MenuServiceImpl implements MenuService {

    private final EventoGastronomicoService eventoGastronomicoService;
    private final ChefService chefService;
    private final ParticipanteService participanteService;
    private final ArchivosEventosService archivosEventosService;
    private final ResenaService resenaService;

    public MenuServiceImpl(EventoGastronomicoService eventoGastronomicoService,
                           ChefService chefService,
                           ParticipanteService participanteService,
                           ArchivosEventosService archivosEventosService,
                           ResenaService resenaService) {
        this.eventoGastronomicoService = eventoGastronomicoService;
        this.chefService = chefService;
        this.participanteService = participanteService;
        this.archivosEventosService = archivosEventosService;
        this.resenaService = resenaService;
    }

    @Override
    public void mostrarMenuBienvenida(Scanner scanner) {
        boolean salir = false;

        while (!salir) {
            System.out.println("Bienvenido al Sistema de Gestión de Eventos Gastronómicos");
            System.out.println("Seleccione su rol:");
            System.out.println("1. Organizador");
            System.out.println("2. Participante");
            System.out.println("3. Salir");
            int rol = scanner.nextInt();
            scanner.nextLine();

            switch (rol) {
                case 1:
                    mostrarMenuOrganizador(scanner);
                    break;
                case 2:
                    mostrarMenuParticipante(scanner);
                    break;
                case 3:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    @Override
    public void mostrarMenuOrganizador(Scanner scanner) {
        boolean volver = false;

        while (!volver) {
            System.out.println("\nMenú Organizador");
            System.out.println("1. Crear Evento");
            System.out.println("2. Gestionar Chefs");
            System.out.println("3. Listar Eventos Disponibles a partir de una fecha");
            System.out.println("4. Exportar Eventos Llenos");
            System.out.println("5. Volver al menú principal");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    eventoGastronomicoService.crearEvento(scanner, chefService.listarChefs());
                    break;
                case 2:
                    gestionarChefs(scanner);
                    break;
                case 3:
                    eventoGastronomicoService.listarEventosDisponibles(scanner);
                    break;
                case 4:
                    archivosEventosService.exportarEventosCsv(BdEventos.getEventosList());
                    break;
                case 5:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    @Override
    public void mostrarMenuParticipante(Scanner scanner) {
        boolean volver = false;
        Participante nuevoParticipante = null;

        while (!volver) {
            System.out.println("\nMenú Participante");
            System.out.println("1. Crear Participante");
            System.out.println("2. Inscribirse en un Evento");
            System.out.println("3. Dejar una Reseña");
            System.out.println("4. Volver al menú principal");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    nuevoParticipante = participanteService.crearParticipante(scanner);
                    System.out.println("Participante creado: " + nuevoParticipante.getNombre() + " " + nuevoParticipante.getApellido() + " con ID: " + nuevoParticipante.getIdParticipante());
                    break;
                case 2:
                    participanteService.inscribirseEnEvento(scanner, eventoGastronomicoService.listarEventos());
                    break;
                case 3:
                    if (nuevoParticipante != null) {
                        resenaService.nuevaResena(scanner, nuevoParticipante);
                    } else {
                        System.out.println("Debes crear un participante primero para dejar una reseña.");
                    }
                    break;
                case 4:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private void gestionarChefs(Scanner scanner) {
        boolean volver = false;

        while (!volver) {
            System.out.println("\nMenú Gestión de Chefs");
            System.out.println("1. Agregar Chef");
            System.out.println("2. Asignar Evento a Chef");
            System.out.println("3. Volver al menú anterior");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    Chef nuevoChef = chefService.agregarChef(scanner);
                    System.out.println("Chef agregado correctamente.");
                    break;
                case 2:
                    chefService.asignarEventoAChef(scanner, chefService.listarChefs(), eventoGastronomicoService.listarEventos());
                    break;
                case 3:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
}
