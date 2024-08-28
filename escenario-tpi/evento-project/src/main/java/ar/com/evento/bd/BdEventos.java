package ar.com.evento.bd;

import ar.com.evento.domain.EventoGastronomico;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.time.LocalDateTime;

public class BdEventos {

    // Método que devuelve una lista de eventos simulada
    public static List<EventoGastronomico> getEventosList() {
        List<EventoGastronomico> eventos = new ArrayList<>();

        // Crear eventos de ejemplo
        EventoGastronomico evento1 = new EventoGastronomico(
                UUID.randomUUID(),
                "Festival de Comida",
                "Un evento gastronómico para disfrutar de comidas de todo el mundo",
                LocalDateTime.of(2024, 10, 1, 12, 0),
                "Centro de Convenciones",
                null,
                200
        );

        EventoGastronomico evento2 = new EventoGastronomico(
                UUID.randomUUID(),
                "Noche de Tapas",
                "Una noche dedicada a las tapas españolas",
                LocalDateTime.of(2024, 11, 5, 19, 0),
                "Restaurante El Rincón",
                null,
                100
        );

        // Agregar eventos a la lista
        eventos.add(evento1);
        eventos.add(evento2);

        return eventos;
    }
}
