package ar.com.evento.service.chef;

import ar.com.evento.domain.Chef;
import ar.com.evento.domain.EventoGastronomico;

import java.util.List;
import java.util.Scanner;

public interface ChefService {

    Chef agregarChef(Scanner scanner);

    void asignarEventoAChef(Scanner scanner, List<Chef> chefsDisponibles, List<EventoGastronomico> eventosDisponibles);

    List<Chef> listarChefs();
}
