package br.edu.ufpel.mapacultural.Controller;

import br.edu.ufpel.mapacultural.Model.Local;
import br.edu.ufpel.mapacultural.Service.UsuarioVinculoService;
import br.edu.ufpel.mapacultural.dto.ArtistaDTO;
import br.edu.ufpel.mapacultural.dto.EventoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/usuarios")
public class UsuarioVinculoController {

    private final UsuarioVinculoService service;

    public UsuarioVinculoController(UsuarioVinculoService service) {
        this.service = service;
    }

    // ===================== INSCRICAO =====================
    @GetMapping("/{id}/eventos")
    public List<EventoDTO> listarEventos(@PathVariable("id") Integer id) {
        return service.listarEventosConfirmados(id);
    }

    @PostMapping("/{id}/eventos/{eventoId}")
    public ResponseEntity<Void> confirmarPresenca(@PathVariable("id") Integer id,
                                                  @PathVariable("eventoId") Integer eventoId) {
        service.confirmarPresenca(id, eventoId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/eventos/{eventoId}")
    public ResponseEntity<Void> cancelarPresenca(@PathVariable("id") Integer id,
                                                 @PathVariable("eventoId") Integer eventoId) {
        service.cancelarPresenca(id, eventoId);
        return ResponseEntity.noContent().build();
    }

    // ===================== SEGUINDO_ARTISTA =====================
    @GetMapping("/{id}/artistas")
    public List<ArtistaDTO> listarArtistasSeguidos(@PathVariable("id") Integer id) {
        return service.listarArtistasSeguidos(id);
    }

    @PostMapping("/{id}/artistas/{artistaId}")
    public ResponseEntity<Void> seguirArtista(@PathVariable("id") Integer id,
                                              @PathVariable("artistaId") Integer artistaId) {
        service.seguirArtista(id, artistaId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/artistas/{artistaId}")
    public ResponseEntity<Void> deixarDeSeguirArtista(@PathVariable("id") Integer id,
                                                      @PathVariable("artistaId") Integer artistaId) {
        service.deixarDeSeguirArtista(id, artistaId);
        return ResponseEntity.noContent().build();
    }

    // ===================== SEGUINDO_LOCAL =====================
    @GetMapping("/{id}/locais")
    public List<Local> listarLocaisSeguidos(@PathVariable("id") Integer id) {
        return service.listarLocaisSeguidos(id);
    }

    @PostMapping("/{id}/locais/{localId}")
    public ResponseEntity<Void> seguirLocal(@PathVariable("id") Integer id,
                                            @PathVariable("localId") Integer localId) {
        service.seguirLocal(id, localId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/locais/{localId}")
    public ResponseEntity<Void> deixarDeSeguirLocal(@PathVariable("id") Integer id,
                                                    @PathVariable("localId") Integer localId) {
        service.deixarDeSeguirLocal(id, localId);
        return ResponseEntity.noContent().build();
    }
}