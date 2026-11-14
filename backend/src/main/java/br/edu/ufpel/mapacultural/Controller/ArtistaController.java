package br.edu.ufpel.mapacultural.Controller;


import br.edu.ufpel.mapacultural.Model.Artista;
import br.edu.ufpel.mapacultural.Repository.ArtistaRepository;
import br.edu.ufpel.mapacultural.Repository.UsuarioRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/artistas")
public class ArtistaController {

    private final ArtistaRepository repository;

    public ArtistaController (ArtistaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Artista> listarArtistas() { return repository.findAll(); }

}
