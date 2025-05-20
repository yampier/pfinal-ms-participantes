package pe.gob.sunat.participantes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.gob.sunat.participantes.entities.Participante;

public interface ParticipanteRepository extends JpaRepository<Participante, String> {}