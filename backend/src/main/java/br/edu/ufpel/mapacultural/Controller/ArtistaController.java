package br.edu.ufpel.mapacultural.Controller;


import br.edu.ufpel.mapacultural.Model.Artista;
import br.edu.ufpel.mapacultural.Repository.ArtistaRepository;
import br.edu.ufpel.mapacultural.Repository.UsuarioRepository;
import br.edu.ufpel.mapacultural.Service.ArtistaService;
import br.edu.ufpel.mapacultural.dto.ArtistaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/artistas")
public class ArtistaController {

    private final ArtistaService service;

    public ArtistaController(ArtistaService service) {
        this.service = service;
    }


    @GetMapping
    public List<ArtistaDTO> listarTodos() {return service.listarTodos();}

    @GetMapping("/{id}")
    public ResponseEntity<ArtistaDTO> buscarPorId(@PathVariable Integer id){
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
