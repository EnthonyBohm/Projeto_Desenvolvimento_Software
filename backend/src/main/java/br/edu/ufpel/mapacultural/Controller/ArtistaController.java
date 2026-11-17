package br.edu.ufpel.mapacultural.Controller;


import br.edu.ufpel.mapacultural.Service.ArtistaService;
import br.edu.ufpel.mapacultural.Model.dto.Request.ArtistaCadastroDTO;
import br.edu.ufpel.mapacultural.Model.dto.Response.ArtistaDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/artistas")
public class ArtistaController {

    private final ArtistaService service;

    public ArtistaController(ArtistaService service) {
        this.service = service;
    }


//=======================    GET     ========================
    @GetMapping
    public List<ArtistaDTO> listarTodos() {return service.listarTodos();}
    @GetMapping("/{id}")
    public ResponseEntity<ArtistaDTO> buscarPorId(@PathVariable Integer id)
    {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

//========================   POST   ========================
    @PostMapping
    public ResponseEntity<ArtistaDTO> cadastrar (@Valid @RequestBody ArtistaCadastroDTO dto)
    {
        ArtistaDTO artista = service.cadastrar(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(artista.usuario_id())
                .toUri();
        return ResponseEntity.created(location).body(artista);
    }


//========================   DELETE   ========================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id)
    {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
