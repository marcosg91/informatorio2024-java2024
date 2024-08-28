package ar.com.evento.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Chef {
    private UUID idChef;
    private String nombre;
    private String especialidad;
    private List<EventoGastronomico> eventosParticipados;

    // Getters y Setters

    public UUID getIdChef() {
        return idChef;
    }

    public void setIdChef(UUID idChef) {
        this.idChef = idChef;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public List<EventoGastronomico> getEventosParticipados() {
        return eventosParticipados;
    }

    public void setEventosParticipados(List<EventoGastronomico> eventosParticipados) {
        this.eventosParticipados = eventosParticipados;
    }

    public Chef(UUID idChef, String nombre, String especialidad) {
        this.idChef = idChef;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.eventosParticipados = new ArrayList<>();
    }

    public void asignarEvento(EventoGastronomico evento) {
        if (evento == null) {
            System.out.println("El evento no puede ser nulo.");
            return;
        }

        if (eventosParticipados.contains(evento)) {
            System.out.println("Este chef ya está asignado a este evento.");
        } else {
            eventosParticipados.add(evento);
            System.out.println("Evento asignado al chef correctamente.");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID Chef: ").append(idChef).append("\n")
                .append("Nombre: ").append(nombre).append("\n")
                .append("Especialidad: ").append(especialidad).append("\n")
                .append("Número de Eventos Participados: ").append(eventosParticipados.size()).append("\n")
                .append("Eventos Participados: ");

        if (eventosParticipados.isEmpty()) {
            sb.append("Ninguno");
        } else {
            sb.append("\n");
            for (EventoGastronomico evento : eventosParticipados) {
                sb.append(" - ").append(evento.getNombre()).append("\n");
            }
        }

        return sb.toString();
    }
}
