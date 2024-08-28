package ar.com.evento.service.archivos.impl;

import ar.com.evento.domain.EventoGastronomico;
import ar.com.evento.service.archivos.ArchivosEventosService;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ArchivosEventosServiceImpl implements ArchivosEventosService {
    private final String UBICACION_ARCHIVO = "\\src\\main\\java\\ar\\com\\evento\\recursos\\";

    private CSVWriter csvWriter;

    @Override
    public void exportarEventosCsv(List<EventoGastronomico> eventos) {
        // Ruta donde se guardará el archivo
        String ruta = System.getProperty("user.dir").concat(UBICACION_ARCHIVO).concat("eventos.csv");

        try {
            this.csvWriter = new CSVWriter(new FileWriter(ruta));
            String[] encabezado = {"ID", "Nombre", "Descripción", "Fecha y Hora", "Ubicación", "Capacidad Máxima", "Chef a Cargo"};
            csvWriter.writeNext(encabezado);

            for (EventoGastronomico evento : eventos) {
                String[] datos = {
                        evento.getIdEvento().toString(),
                        evento.getNombre(),
                        evento.getDescripcion(),
                        evento.getFechaHora().toString(),
                        evento.getUbicacion(),
                        Integer.toString(evento.getCupoMaximo()),
                        evento.getChefPrincipal() != null ? evento.getChefPrincipal().getNombre() : "N/A"
                };
                this.csvWriter.writeNext(datos);
            }

            System.out.println("Datos exportados correctamente");
        } catch (IOException e) {
            System.out.println("Algo salió mal: " + e.getMessage() + " - Ubicación del archivo: " + ruta);
        } finally {
            cerrarWriter();
        }
    }

    @Override
    public void cerrarWriter() {
        if (this.csvWriter != null) {
            try {
                this.csvWriter.close();
            } catch (IOException e) {
                System.out.println("Algo salió mal: " + e.getMessage());
            }
        }
    }
}
