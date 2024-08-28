package ar.com.evento.service.participante.impl;

import ar.com.evento.domain.EventoGastronomico;
import ar.com.evento.domain.Participante;
import ar.com.evento.domain.Resena;
import ar.com.evento.service.participante.ParticipanteService;
import ar.com.evento.service.resena.ResenaService;
import ar.com.evento.service.resena.impl.ResenaServiceImpl;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class ParticipanteServiceImpl implements ParticipanteService {

    private Participante participanteActual;
    private ResenaService resenaService = new ResenaServiceImpl();

    @Override
    public Participante crearParticipante(Scanner scanner) {
        System.out.print("Ingrese el nombre del participante: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese el apellido del participante: ");
        String apellido = scanner.nextLine();

        System.out.print("Ingrese los intereses culinarios del participante (separados por comas): ");
        String interesesCulinariosStr = scanner.nextLine();
        List<String> interesesCulinarios = List.of(interesesCulinariosStr.split(",\\s*"));

        return new Participante(UUID.randomUUID(), nombre, apellido, interesesCulinarios);
    }

    @Override
    public void inscribirseEnEvento(Scanner scanner, List<EventoGastronomico> eventosDisponibles) {
        if (participanteActual == null) {
            System.out.println("No hay un participante activo. Por favor, cree un participante primero.");
            return;
        }

        System.out.println("Ingrese el ID del evento en el que desea inscribir al participante:");
        String idEvento = scanner.nextLine();

        EventoGastronomico eventoSeleccionado = eventosDisponibles.stream()
                .filter(e -> e.getIdEvento().equals(idEvento))
                .findFirst()
                .orElse(null);

        if (eventoSeleccionado == null) {
            System.out.println("ID de evento no válido.");
            return;
        }

        if (participanteActual.getHistorialEventos().contains(eventoSeleccionado)) {
            System.out.println("El participante ya está inscrito en este evento.");
        } else {
            participanteActual.getHistorialEventos().add(eventoSeleccionado);
            System.out.println("Inscripción en el evento realizada con éxito.");
        }
    }

    @Override
    public void dejarResena(Scanner scanner) {
        if (participanteActual == null) {
            System.out.println("No hay un participante activo. Por favor, cree un participante primero.");
            return;
        }

        Resena resena = resenaService.nuevaResena(scanner, participanteActual);
        if (resena != null) {
            System.out.println("Reseña dejada exitosamente.");
        }
    }
}
