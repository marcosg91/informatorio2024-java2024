package ar.com.evento.service.eventogastronomico.impl;

import ar.com.evento.domain.Chef;
import ar.com.evento.domain.EventoGastronomico;
import ar.com.evento.service.chef.impl.ChefServiceImpl;
import ar.com.evento.service.eventogastronomico.EventoGastronomicoService;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class EventoGastronomicoServiceImpl implements EventoGastronomicoService {

    private final List<EventoGastronomico> eventosDisponibles = new ArrayList<>();
    private final ChefServiceImpl chefService = new ChefServiceImpl();  // Asumimos que tienes una instancia de ChefServiceImpl

    @Override
    public void crearEvento(Scanner scanner, List<Chef> chefsDisponibles) {
        System.out.print("Ingrese el nombre del evento: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese la descripción del evento: ");
        String descripcion = scanner.nextLine();

        System.out.print("Ingrese la fecha y hora del evento (YYYY-MM-DDTHH:MM): ");
        LocalDateTime fechaHora = LocalDateTime.parse(scanner.nextLine());

        System.out.print("Ingrese la ubicación del evento: ");
        String ubicacion = scanner.nextLine();

        System.out.println("Seleccione el chef principal por ID:");
        for (Chef chef : chefsDisponibles) {
            System.out.println(chef.getIdChef() + ": " + chef.getNombre() + " (" + chef.getEspecialidad() + ")");
        }

        System.out.print("Ingrese el ID del chef: ");
        UUID idChef = UUID.fromString(scanner.nextLine());

        Chef chefSeleccionado = chefService.buscarChefPorId(idChef);

        if (chefSeleccionado == null) {
            System.out.println("ID de chef no válido.");
            return;
        }

        System.out.print("Ingrese el cupo máximo de participantes: ");
        int cupoMaximo = scanner.nextInt();
        scanner.nextLine();

        EventoGastronomico nuevoEvento = new EventoGastronomico(UUID.randomUUID(), nombre, descripcion, fechaHora, ubicacion, chefSeleccionado, cupoMaximo);
        eventosDisponibles.add(nuevoEvento);
    }

    @Override
    public void listarEventosDisponibles(Scanner scanner) {
        System.out.print("Ingrese la fecha a partir de la cual desea listar los eventos (YYYY-MM-DD): ");
        String inputFecha = scanner.nextLine();
        LocalDateTime fecha;
        try {
            fecha = LocalDateTime.parse(inputFecha + "T00:00");
        } catch (DateTimeParseException e) {
            System.out.println("Formato de fecha no válido. Por favor, use YYYY-MM-DD.");
            return;
        }

        System.out.println("Eventos disponibles a partir de la fecha: " + fecha.toLocalDate());
        boolean hayEventos = false;

        for (EventoGastronomico evento : eventosDisponibles) {
            if (!evento.getFechaHora().isBefore(fecha)) {
                System.out.println("ID: " + evento.getIdEvento());
                System.out.println("Nombre: " + evento.getNombre());
                System.out.println("Fecha: " + evento.getFechaHora().toLocalDate());
                System.out.println("Ubicación: " + evento.getUbicacion());
                System.out.println("Chef Principal: " + evento.getChefPrincipal().getNombre());
                System.out.println("Cupo Máximo: " + evento.getCupoMaximo());
                System.out.println("------------------------");
                hayEventos = true;
            }
        }

        if (!hayEventos) {
            System.out.println("No hay eventos disponibles después de esta fecha.");
        }
    }

    @Override
    public List<EventoGastronomico> listarEventos() {
        return eventosDisponibles;
    }

    // Nuevo método para buscar un evento por ID
    public EventoGastronomico buscarEventoPorId(UUID idEvento) {
        return eventosDisponibles.stream()
                .filter(evento -> evento.getIdEvento().equals(idEvento))
                .findFirst()
                .orElse(null);
    }
}
