package ar.com.instituto.service.archivos.impl;

import ar.com.instituto.domain.Curso;
import ar.com.instituto.service.archivos.ArchivosCursosService;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ArchivosCursosServiceImpl implements ArchivosCursosService {
    private final String UBICACION_ARCHIVO = "\\src\\main\\java\\ar\\com\\instituto\\recursos\\";

    CSVWriter csvWriter;

    @Override
    public void exportarCursosCsv(List<Curso> cursos) {

        //ruta donde se guardara el archivo
        String ruta = System.getProperty("user.dir").concat(UBICACION_ARCHIVO).concat("nuevos-cursos.csv");

        try{
            this.csvWriter = new CSVWriter(new FileWriter(ruta));
            String[] encabezado = {"ID", "NOMBRE", "CANTIDAD DE HORAS", "COMPLEJIDAD"};
            csvWriter.writeNext(encabezado);

            for (Curso curso : cursos) {
                String[] datos = {
                        curso.getId().toString(),
                        curso.getNombre(),
                        curso.getCantidadHoras().toString(),
                        curso.getComplejidad().toString()
                };
                this.csvWriter.writeNext(datos);
            }

            System.out.println("Datos exportados correctamente");
        }catch (IOException e){
            System.out.println("Algo salio mal : " + e.getMessage().concat("ubicacion del archivo : " + ruta));
        }
    }

    @Override
    public void cerrarWriter() {
        if(this.csvWriter != null) {
            try {
                this.csvWriter.close();
            }catch (IOException e){
                System.out.println("Algo salio mal : " + e.getMessage());
            }
        }
    }
}
