package ar.com.evento.service.eventogastronomico;

import ar.com.evento.domain.Chef;
import ar.com.evento.domain.EventoGastronomico;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public interface EventoGastronomicoService {
    void crearEvento(Scanner scanner, List<Chef> chefsDisponibles);

    void listarEventosDisponibles(Scanner scanner);

    List<EventoGastronomico> listarEventos();

    EventoGastronomico buscarEventoPorId(UUID idEvento);
}
