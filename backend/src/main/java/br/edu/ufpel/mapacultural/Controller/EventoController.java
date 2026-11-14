package br.edu.ufpel.mapacultural.Controller;

import br.edu.ufpel.mapacultural.Model.Evento;
import br.edu.ufpel.mapacultural.Repository.EventoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping ("/eventos")
public class EventoController {

    private final EventoRepository repository;

    public EventoController (EventoRepository repository){
        this.repository = repository;
    }

    @GetMapping
    public List<Evento> listarEventos() { return repository.findAll(); }

}
