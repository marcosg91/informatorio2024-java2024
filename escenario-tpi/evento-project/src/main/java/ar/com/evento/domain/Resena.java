package ar.com.evento.domain;

import java.util.UUID;

public class Resena {
    private UUID idResena;
    private EventoGastronomico evento;
    private Participante participante;
    private int calificacion;
    private String comentario;

    // Getters y Setters

    public UUID getIdResena() {
        return idResena;
    }

    public void setIdResena(UUID idResena) {
        this.idResena = idResena;
    }

    public EventoGastronomico getEvento() {
        return evento;
    }

    public void setEvento(EventoGastronomico evento) {
        this.evento = evento;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Resena(UUID idResena, EventoGastronomico evento, Participante participante, int calificacion, String comentario) {
        this.idResena = idResena;
        this.evento = evento;
        this.participante = participante;
        this.calificacion = calificacion;
        this.comentario = comentario;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID Reseña: ").append(idResena).append("\n")
                .append("Evento: ").append(evento != null ? evento.getNombre() : "No especificado").append("\n")
                .append("Participante: ").append(participante != null ? participante.getNombre() + " " + participante.getApellido() : "No especificado").append("\n")
                .append("Calificación: ").append(calificacion).append("\n")
                .append("Comentario: ").append(comentario != null ? comentario : "Ninguno").append("\n");

        return sb.toString();
    }
}
