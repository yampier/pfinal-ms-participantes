package pe.gob.sunat.participantes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import feign.FeignException;
import pe.gob.sunat.participantes.dto.EventoDTO;
import pe.gob.sunat.participantes.entities.Inscripcion;
import pe.gob.sunat.participantes.entities.Participante;
import pe.gob.sunat.participantes.feign.EventoClient;
import pe.gob.sunat.participantes.repository.InscripcionRepository;
import pe.gob.sunat.participantes.repository.ParticipanteRepository;

@Service
public class InscripcionServiceImpl implements InscripcionService {

    private final InscripcionRepository inscripcionRepo;
    private final ParticipanteRepository participanteRepo;
    private final EventoClient eventoClient;

    public InscripcionServiceImpl(
            InscripcionRepository inscripcionRepo,
            ParticipanteRepository participanteRepo,
            EventoClient eventoClient
    ) {
        this.inscripcionRepo = inscripcionRepo;
        this.participanteRepo = participanteRepo;
        this.eventoClient = eventoClient;
    }

    @Override
    public ResponseEntity<String> registrar(String dni, String codigoEvento) {
        // Buscar el participante
        Optional<Participante> optParticipante = participanteRepo.findById(dni);
        if (optParticipante.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Participante no encontrado con DNI: " + dni);
        }

        // Obtener el evento mediante el cliente Feign
        EventoDTO eventoDTO;
        try {
            eventoDTO = eventoClient.obtenerEvento(codigoEvento);
        } catch (FeignException.NotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Evento no encontrado con código: " + codigoEvento);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body("Error de comunicación con el microservicio de eventos.");
        }

        Participante participante = optParticipante.get();
        
        // Verificar si el participante ya está inscrito en el evento
        if (inscripcionRepo.existsByParticipanteAndEventoId(participante, codigoEvento)) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("El participante ya está inscrito en el evento con código: " + codigoEvento);
        }

        // Verificar si la capacidad máxima del evento ha sido alcanzada
        long inscritos = inscripcionRepo.countByEventoId(codigoEvento);
        if (inscritos >= eventoDTO.getCapacidadMaxima()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Capacidad máxima alcanzada para el evento: " + codigoEvento);
        }

        // Registrar la nueva inscripción
        Inscripcion nueva = new Inscripcion();
        nueva.setParticipante(participante);
        nueva.setEventoId(codigoEvento);
        inscripcionRepo.save(nueva);

        return ResponseEntity.status(HttpStatus.CREATED).body("Inscripción exitosa.");
    }

    @Override
    public List<Inscripcion> listarPorDni(String dni) {
        return inscripcionRepo.findByParticipanteDni(dni);
    }
}