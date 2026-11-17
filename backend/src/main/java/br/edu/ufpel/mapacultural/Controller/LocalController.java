package br.edu.ufpel.mapacultural.Controller;


import br.edu.ufpel.mapacultural.Model.Local;
import br.edu.ufpel.mapacultural.Service.EventoService;
import br.edu.ufpel.mapacultural.Service.LocalService;
import br.edu.ufpel.mapacultural.dto.EventoDTO;
import br.edu.ufpel.mapacultural.dto.LocalCadastroDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/locais")
public class LocalController {

    private final LocalService service;
    private final EventoService eventoService;

    public LocalController(LocalService service, EventoService eventoService) {
        this.service = service;
        this.eventoService = eventoService;
    }

//===========================       GET       ========================
    @GetMapping
    public List<Local> listarTodos() {
        return service.listarTodos();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Local> buscarPorId(@PathVariable("id") Integer id)
    {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/{id}/eventos")
    public List<EventoDTO> listarEventos(@PathVariable("id") Integer id) { return eventoService.listarPorLocal(id); }

    @GetMapping("/{id}/eventos/{eventoId}")
    public ResponseEntity<EventoDTO> buscarEvento (@PathVariable("id") Integer id, @PathVariable("eventoId") Integer eventoId)
    {
        return eventoService
                .buscarPorEvento(id, eventoId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

//===========================       POST       ========================
    @PostMapping
    public ResponseEntity<Local> cadastrar (@Valid @RequestBody LocalCadastroDTO dto)
    {
        Local local = service.cadastrar(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(local.getLocalId())
                .toUri();
        return ResponseEntity.created(location).body(local);
    }

//========================= DELETE ========================================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir (@PathVariable("id") Integer id)
    {
        service.excluir(id);

        return ResponseEntity.noContent().build();
    }

}
