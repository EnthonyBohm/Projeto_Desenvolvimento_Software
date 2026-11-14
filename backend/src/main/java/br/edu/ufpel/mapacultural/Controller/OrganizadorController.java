package br.edu.ufpel.mapacultural.Controller;


import br.edu.ufpel.mapacultural.Model.Organizador;
import br.edu.ufpel.mapacultural.Repository.OrganizadorRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/organizadores")
public class OrganizadorController {

    private final OrganizadorRepository repository;

    public OrganizadorController (OrganizadorRepository repository){
        this.repository = repository;
    }

    @GetMapping
    public List<Organizador> listarOrganizadores() { return repository.findAll(); }
}
