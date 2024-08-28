package ar.com.evento.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class EventoGastronomico {

    private UUID idEvento;
    private String nombre;
    private String descripcion;
    private LocalDateTime fechaHora;
    private String ubicacion;
    private Chef chefPrincipal;
    private int cupoMaximo;
    private List<Resena> resenas;

    // Getters y Setters

    public UUID getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(UUID idEvento) {
        this.idEvento = idEvento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Chef getChefPrincipal() {
        return chefPrincipal;
    }

    public void setChefPrincipal(Chef chefPrincipal) {
        this.chefPrincipal = chefPrincipal;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    public List<Resena> getResenas() {
        return resenas;
    }

    public void setResenas(List<Resena> resenas) {
        this.resenas = resenas;
    }

    public EventoGastronomico(UUID idEvento, String nombre, String descripcion, LocalDateTime fechaHora, String ubicacion, Chef chefPrincipal, int cupoMaximo) {
        this.idEvento = idEvento;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaHora = fechaHora;
        this.ubicacion = ubicacion;
        this.chefPrincipal = chefPrincipal;
        this.cupoMaximo = cupoMaximo;
    }

    public void agregarResena(Resena resena) {
        resenas.add(resena);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append("ID del Evento: ")
                .append(this.getIdEvento()).append("\n")
                .append("Nombre: ")
                .append(this.getNombre()).append("\n")
                .append("Descripción: ")
                .append(this.getDescripcion()).append("\n")
                .append("Fecha y Hora: ")
                .append(this.getFechaHora()).append("\n")
                .append("Ubicación: ")
                .append(this.getUbicacion()).append("\n")
                .append("Cupo Máximo: ")
                .append(this.getCupoMaximo()).append("\n")
                .append("Chef Principal: ")
                .append(this.getChefPrincipal() != null ? this.getChefPrincipal().getNombre() : "N/A").append("\n")
                .append("-------------------------------------------------------\n")
                .toString();
    }
}
