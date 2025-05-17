package pe.gob.sunat.participantes.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "INSCRIPCION", uniqueConstraints = {
    @UniqueConstraint(columnNames = { "DNI_PARTICIPANTE" })
})
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "DNI_PARTICIPANTE", referencedColumnName = "DNI")
    private Participante participante;

    @Column(name = "EVENTO_ID", nullable = false)
    private String eventoId;

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public String getEventoId() {
        return eventoId;
    }

    public void setEventoId(String eventoId) {
        this.eventoId = eventoId;
    }

    // Constructor
    public Inscripcion() {}

    public Inscripcion(Participante participante, String eventoId) {
        this.participante = participante;
        this.eventoId = eventoId;
    }
}