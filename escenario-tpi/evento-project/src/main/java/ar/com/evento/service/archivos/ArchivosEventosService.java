package ar.com.evento.service.archivos;

import ar.com.evento.domain.EventoGastronomico;

import java.util.List;

public interface ArchivosEventosService {
    void exportarEventosCsv(List<EventoGastronomico> eventoGastronomico);

    void cerrarWriter();
}
