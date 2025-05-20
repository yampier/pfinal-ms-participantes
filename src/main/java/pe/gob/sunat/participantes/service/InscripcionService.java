package pe.gob.sunat.participantes.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import pe.gob.sunat.participantes.entities.Inscripcion;

public interface InscripcionService {
    ResponseEntity<String> registrar(String dni, String codigoEvento);
    List<Inscripcion> listarPorDni(String dni);
}