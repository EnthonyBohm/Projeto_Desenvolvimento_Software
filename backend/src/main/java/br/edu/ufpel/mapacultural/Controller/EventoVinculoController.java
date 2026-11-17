package br.edu.ufpel.mapacultural.Controller;

import br.edu.ufpel.mapacultural.Service.EventoVinculoService;
import br.edu.ufpel.mapacultural.dto.ArtistaDTO;
import br.edu.ufpel.mapacultural.dto.UsuarioDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/eventos")
public class EventoVinculoController {

    private final EventoVinculoService service;

    public EventoVinculoController(EventoVinculoService service) {
        this.service = service;
    }

    // ===================== INSCRICAO (lado do evento) =====================
    @GetMapping("/{id}/usuarios")
    public List<UsuarioDTO> listarInscritos(@PathVariable("id") Integer id) {
        return service.listarInscritos(id);
    }

    // ===================== PARTICIPA_EVENTO =====================
    @GetMapping("/{id}/artistas")
    public List<ArtistaDTO> listarArtistas(@PathVariable("id") Integer id) {
        return service.listarArtistas(id);
    }

    @PostMapping("/{id}/artistas/{artistaId}")
    public ResponseEntity<Void> vincularArtista(@PathVariable("id") Integer id,
                                                @PathVariable("artistaId") Integer artistaId) {
        service.vincularArtista(id, artistaId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/artistas/{artistaId}")
    public ResponseEntity<Void> desvincularArtista(@PathVariable("id") Integer id,
                                                   @PathVariable("artistaId") Integer artistaId) {
        service.desvincularArtista(id, artistaId);
        return ResponseEntity.noContent().build();
    }
}