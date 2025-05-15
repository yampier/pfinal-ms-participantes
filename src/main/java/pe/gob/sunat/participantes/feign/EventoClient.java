package pe.gob.sunat.participantes.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import pe.gob.sunat.participantes.dto.EventoDTO;

@FeignClient(name = "ms-eventos", url = "http://ms-eventos:9084")
//@FeignClient(name = "ms-eventos", url = "http://localhost:9084")
public interface EventoClient {

    @GetMapping("/eventos/{codigo}")
    EventoDTO obtenerEvento(@PathVariable String codigo);
}