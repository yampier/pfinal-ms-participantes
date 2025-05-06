package pe.gob.sunat.participantes.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "EVENTO")
public class Evento {

    @Id
    @Column(name = "CODIGO_EVENTO", length = 20)
    private String codigoEvento;

    private String nombre;
    private String descripcion;
    private LocalDateTime fechaHora;
    private String ubicacion;
    private int capacidadMaxima;

    // Constructores
    public Evento() {}

    public Evento(String codigoEvento, String nombre, String descripcion, LocalDateTime fechaHora, String ubicacion, int capacidadMaxima) {
        this.codigoEvento = codigoEvento;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaHora = fechaHora;
        this.ubicacion = ubicacion;
        this.capacidadMaxima = capacidadMaxima;
    }

    // Getters y setters
    public String getCodigoEvento() {
		return codigoEvento;
	}
    
    public void setCodigoEvento(String codigoEvento) {
		this.codigoEvento = codigoEvento;
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

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }
}