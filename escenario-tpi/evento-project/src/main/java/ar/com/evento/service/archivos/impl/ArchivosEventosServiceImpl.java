package ar.com.evento.service.archivos.impl;

import ar.com.evento.domain.EventoGastronomico;
import ar.com.evento.service.archivos.ArchivosEventosService;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class ArchivosEventosServiceImpl implements ArchivosEventosService {
    private final String UBICACION_ARCHIVO = "src/main/java/ar/com/evento/recursos/";

    private CSVWriter csvWriter;

    @Override
    public void exportarEventosCsv(List<EventoGastronomico> eventos) {
        // Ruta donde se guardará el archivo
        File archivo = Paths.get(UBICACION_ARCHIVO, "eventos.csv").toFile();
        File directorio = archivo.getParentFile();

        // Crear directorio si no existe
        if (!directorio.exists()) {
            if (!directorio.mkdirs()) {
                System.out.println("No se pudo crear el directorio: " + directorio.getAbsolutePath());
                return;
            }
        }

        try {
            this.csvWriter = new CSVWriter(new FileWriter(archivo));
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
            System.out.println("Algo salió mal: " + e.getMessage() + " - Ubicación del archivo: " + archivo.getAbsolutePath());
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
