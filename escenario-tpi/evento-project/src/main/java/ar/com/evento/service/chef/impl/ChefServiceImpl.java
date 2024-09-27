package ar.com.evento.service.chef.impl;

import ar.com.evento.domain.Chef;
import ar.com.evento.domain.EventoGastronomico;
import ar.com.evento.service.chef.ChefService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class ChefServiceImpl implements ChefService {

    private final List<Chef> chefsDisponibles = new ArrayList<>();

    @Override
    public Chef agregarChef(Scanner scanner) {
        System.out.print("Ingrese el nombre del chef: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese la especialidad del chef: ");
        String especialidad = scanner.nextLine();

        UUID idChef = UUID.randomUUID();

        Chef nuevoChef = new Chef(idChef, nombre, especialidad);
        chefsDisponibles.add(nuevoChef);

        System.out.println("Nuevo chef agregado correctamente: " + nombre);

        return nuevoChef;
    }

    @Override
    public void asignarEventoAChef(Scanner scanner, List<Chef> chefsDisponibles, List<EventoGastronomico> eventosDisponibles) {
        System.out.println("Ingrese el ID del evento al que desea asignar un chef:");
        String idEvento = scanner.nextLine();

        EventoGastronomico eventoSeleccionado = eventosDisponibles.stream()
                .filter(e -> e.getIdEvento().equals(idEvento))
                .findFirst()
                .orElse(null);

        if (eventoSeleccionado == null) {
            System.out.println("ID de evento no válido.");
            return;
        }

        System.out.println("Ingrese el ID del chef que desea asignar al evento:");
        String idChefStr = scanner.nextLine();

        UUID idChef = UUID.fromString(idChefStr);

        Chef chefSeleccionado = chefsDisponibles.stream()
                .filter(c -> c.getIdChef().equals(idChef))
                .findFirst()
                .orElse(null);

        if (chefSeleccionado == null) {
            System.out.println("ID de chef no válido.");
            return;
        }

        chefSeleccionado.asignarEvento(eventoSeleccionado);
    }

    @Override
    public List<Chef> listarChefs() {
        return new ArrayList<>(chefsDisponibles);
    }

    // Nuevo método para buscar un chef por ID
    public Chef buscarChefPorId(UUID idChef) {
        return chefsDisponibles.stream()
                .filter(chef -> chef.getIdChef().equals(idChef))
                .findFirst()
                .orElse(null);
    }
}
