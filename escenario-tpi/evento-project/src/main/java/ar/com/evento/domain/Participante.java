package ar.com.evento.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Participante {
    private UUID idParticipante;
    private String nombre;
    private String apellido;
    private List<String> interesesCulinarios;
    private List<EventoGastronomico> historialEventos;

    // Getters y Setters

    public UUID getIdParticipante() {
        return idParticipante;
    }

    public void setIdParticipante(UUID idParticipante) {
        this.idParticipante = idParticipante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public List<String> getInteresesCulinarios() {
        return interesesCulinarios;
    }

    public void setInteresesCulinarios(List<String> interesesCulinarios) {
        this.interesesCulinarios = interesesCulinarios;
    }

    public List<EventoGastronomico> getHistorialEventos() {
        return historialEventos;
    }

    public void setHistorialEventos(List<EventoGastronomico> historialEventos) {
        this.historialEventos = historialEventos;
    }

    public Participante(UUID idParticipante, String nombre, String apellido, List<String> interesesCulinarios) {
        this.idParticipante = idParticipante;
        this.nombre = nombre;
        this.apellido = apellido;
        this.interesesCulinarios = interesesCulinarios;
        this.historialEventos = new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID Participante: ").append(idParticipante).append("\n")
                .append("Nombre: ").append(nombre).append("\n")
                .append("Apellido: ").append(apellido).append("\n")
                .append("Intereses Culinarios: ").append(interesesCulinarios.isEmpty() ? "Ninguno" : String.join(", ", interesesCulinarios)).append("\n")
                .append("Historial de Eventos: \n");

        if (historialEventos.isEmpty()) {
            sb.append("Ninguno");
        } else {
            for (EventoGastronomico evento : historialEventos) {
                sb.append(" - ").append(evento.getNombre()).append("\n");
            }
        }

        return sb.toString();
    }
}
