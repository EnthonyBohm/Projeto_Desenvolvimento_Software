package br.edu.ufpel.mapacultural.Controller;

import br.edu.ufpel.mapacultural.Model.Evento;
import br.edu.ufpel.mapacultural.Repository.EventoRepository;
import br.edu.ufpel.mapacultural.Service.EventoService;
import br.edu.ufpel.mapacultural.dto.EventoCadastroDTO;
import br.edu.ufpel.mapacultural.dto.EventoDTO;
import br.edu.ufpel.mapacultural.dto.OrganizadorDTO;
import br.edu.ufpel.mapacultural.dto.UsuarioDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping ("/eventos")
public class EventoController {

    private final EventoService service;

    public EventoController(EventoService service) {
        this.service = service;
    }

//======================    GET     ========================
    @GetMapping
    public List<EventoDTO> listarTodos() {return service.listarTodos(); }
    @GetMapping("/{id}")
    public ResponseEntity<EventoDTO> buscarPorId(@PathVariable Integer id){
        return service.buscaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

//======================    POST     ========================
    @PostMapping
    public ResponseEntity<EventoDTO> cadastrar (@Valid @RequestBody EventoCadastroDTO dto)
    {
        EventoDTO evento = service.cadastrar(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(evento.id())
                .toUri();
        return ResponseEntity.created(location).body(evento);
    }

//========================= DELETE ========================================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir (@PathVariable Integer id)
    {
        service.excluir(id);

        return ResponseEntity.noContent().build();
    }

}
