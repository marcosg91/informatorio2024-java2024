package ar.com.evento.service.resena.impl;

import ar.com.evento.domain.EventoGastronomico;
import ar.com.evento.domain.Participante;
import ar.com.evento.domain.Resena;
import ar.com.evento.service.eventogastronomico.EventoGastronomicoService;
import ar.com.evento.service.resena.ResenaService;

import java.util.Scanner;
import java.util.UUID;

public class ResenaServiceImpl implements ResenaService {

    private final EventoGastronomicoService eventoGastronomicoService;

    public ResenaServiceImpl(EventoGastronomicoService eventoGastronomicoService) {
        this.eventoGastronomicoService = eventoGastronomicoService;
    }

    @Override
    public Resena nuevaResena(Scanner scanner, Participante participante) {
        if (participante.getHistorialEventos() == null || participante.getHistorialEventos().isEmpty()) {
            System.out.println("El participante no tiene eventos en su historial.");
            return null;
        }

        System.out.println("Seleccione el ID del evento para dejar una reseña:");
        for (EventoGastronomico evento : participante.getHistorialEventos()) {
            System.out.println(evento.getIdEvento() + ": " + evento.getNombre());
        }

        UUID idEvento = UUID.fromString(scanner.nextLine()); // Cambiado a UUID

        // Usamos el método buscarEventoPorId del servicio de eventos
        EventoGastronomico eventoSeleccionado = eventoGastronomicoService.buscarEventoPorId(idEvento);

        if (eventoSeleccionado == null) {
            System.out.println("ID de evento no válido.");
            return null;
        }

        if (!participante.getHistorialEventos().contains(eventoSeleccionado)) {
            System.out.println("El participante no asistió a este evento.");
            return null;
        }

        System.out.print("Ingrese la calificación (1 a 5): ");
        int calificacion = scanner.nextInt();
        scanner.nextLine();

        if (calificacion < 1 || calificacion > 5) {
            System.out.println("Calificación no válida. Debe estar entre 1 y 5.");
            return null;
        }

        System.out.print("Ingrese el comentario: ");
        String comentario = scanner.nextLine();

        Resena resena = new Resena(
                UUID.randomUUID(),
                eventoSeleccionado,
                participante,
                calificacion,
                comentario
        );

        eventoSeleccionado.agregarResena(resena); // Agrega la reseña al evento
        System.out.println("Reseña añadida al evento: " + eventoSeleccionado.getNombre());
        return resena;
    }
}
