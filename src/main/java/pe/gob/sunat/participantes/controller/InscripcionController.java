package pe.gob.sunat.participantes.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pe.gob.sunat.participantes.service.InscripcionService;
import pe.gob.sunat.participantes.dto.InscripcionRequest;
import pe.gob.sunat.participantes.entities.Inscripcion;

import java.util.List;

@RestController
@RequestMapping("/inscripciones")
public class InscripcionController {

    private final InscripcionService inscripcionService;

    public InscripcionController(InscripcionService inscripcionService) {
        this.inscripcionService = inscripcionService;
    }

    // Endpoint para registrar una nueva inscripción
    @PostMapping
    public ResponseEntity<String> registrarInscripcion(@RequestBody InscripcionRequest request) {
        return inscripcionService.registrar(request.getDni(), request.getCodigoEvento());
    }

    // Endpoint para listar todas las inscripciones de un participante por su DNI
    @GetMapping
    public List<Inscripcion> listarInscripcionesPorDni(@RequestParam String dni) {
        return inscripcionService.listarPorDni(dni);
    }
}