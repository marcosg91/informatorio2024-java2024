package ar.com.evento.service.resena;

import ar.com.evento.domain.Participante;
import ar.com.evento.domain.Resena;

import java.util.Scanner;

public interface ResenaService {

    Resena nuevaResena(Scanner scanner, Participante participante);
}
