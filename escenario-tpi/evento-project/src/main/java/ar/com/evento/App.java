package ar.com.evento;

import ar.com.evento.service.archivos.ArchivosEventosService;
import ar.com.evento.service.archivos.impl.ArchivosEventosServiceImpl;
import ar.com.evento.service.chef.ChefService;
import ar.com.evento.service.chef.impl.ChefServiceImpl;
import ar.com.evento.service.participante.ParticipanteService;
import ar.com.evento.service.participante.impl.ParticipanteServiceImpl;
import ar.com.evento.service.eventogastronomico.EventoGastronomicoService;
import ar.com.evento.service.eventogastronomico.impl.EventoGastronomicoServiceImpl;
import ar.com.evento.service.menu.MenuService;
import ar.com.evento.service.menu.impl.MenuServiceImpl;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        EventoGastronomicoService eventoGastronomicoService = new EventoGastronomicoServiceImpl();
        ParticipanteService participanteService = new ParticipanteServiceImpl();
        ChefService chefService = new ChefServiceImpl();
        ArchivosEventosService archivosEventosService = new ArchivosEventosServiceImpl();

        MenuService menuService = new MenuServiceImpl(eventoGastronomicoService, chefService, participanteService, archivosEventosService);

        Scanner scanner = new Scanner(System.in);
        menuService.mostrarMenuBienvenida(scanner);
        scanner.close();
    }
}
