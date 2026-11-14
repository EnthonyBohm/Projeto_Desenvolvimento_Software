package br.edu.ufpel.mapacultural.Controller;


import br.edu.ufpel.mapacultural.Model.Local;
import br.edu.ufpel.mapacultural.Repository.LocalRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/locais")
public class LocalController {

    private final LocalRepository repository;

    public LocalController (LocalRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Local> listarLocais() { return repository.findAll(); }
}
