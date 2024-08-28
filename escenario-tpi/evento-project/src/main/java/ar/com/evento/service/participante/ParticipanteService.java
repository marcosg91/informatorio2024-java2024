package ar.com.evento.service.participante;

import ar.com.evento.domain.EventoGastronomico;
import ar.com.evento.domain.Participante;

import java.util.List;
import java.util.Scanner;

public interface ParticipanteService {
    Participante crearParticipante(Scanner scanner);

    void inscribirseEnEvento(Scanner scanner, List<EventoGastronomico> eventosDisponibles);

    void dejarResena(Scanner scanner);
}
