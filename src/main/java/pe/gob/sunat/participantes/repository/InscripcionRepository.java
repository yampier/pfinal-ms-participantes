package pe.gob.sunat.participantes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.gob.sunat.participantes.entities.Inscripcion;
import pe.gob.sunat.participantes.entities.Participante;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
	boolean existsByParticipanteAndEventoId(Participante participante, String eventoId);
    List<Inscripcion> findByParticipanteDni(String dni);
    long countByEventoId(String eventoId);
}