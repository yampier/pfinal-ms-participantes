package pe.gob.sunat.participantes.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "INSCRIPCION", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "DNI_PARTICIPANTE", "CODIGO_EVENTO" }) })
public class Inscripcion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "DNI_PARTICIPANTE", referencedColumnName = "dni")
	private Participante participante;

	@ManyToOne(optional = false)
	@JoinColumn(name = "EVENTO_ID", referencedColumnName = "CODIGO_EVENTO")
	private Evento evento;

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

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	// Constructor
	public Inscripcion() {
	}

	public Inscripcion(Participante participante, Evento evento) {
		this.participante = participante;
		this.evento = evento;
	}
}