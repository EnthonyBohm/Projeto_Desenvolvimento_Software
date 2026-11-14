package br.edu.ufpel.mapacultural.Controller;

import br.edu.ufpel.mapacultural.Model.Evento;
import br.edu.ufpel.mapacultural.Repository.EventoRepository;
import br.edu.ufpel.mapacultural.Service.EventoService;
import br.edu.ufpel.mapacultural.dto.EventoDTO;
import br.edu.ufpel.mapacultural.dto.UsuarioDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping ("/eventos")
public class EventoController {

    private final EventoService service;

    public EventoController(EventoService service) {
        this.service = service;
    }

    @GetMapping
    public List<EventoDTO> listarTodos() {return service.listarTodos(); }

    @GetMapping("/{id}")
    public ResponseEntity<EventoDTO> buscarPorId(@PathVariable Integer id){
        return service.buscaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
