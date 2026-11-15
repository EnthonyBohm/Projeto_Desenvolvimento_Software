package br.edu.ufpel.mapacultural.Controller;


import br.edu.ufpel.mapacultural.Model.Organizador;
import br.edu.ufpel.mapacultural.Repository.OrganizadorRepository;
import br.edu.ufpel.mapacultural.Service.OrganizadorService;
import br.edu.ufpel.mapacultural.dto.OrganizadorCadastroDTO;
import br.edu.ufpel.mapacultural.dto.OrganizadorDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/organizadores")
public class OrganizadorController {

    private final OrganizadorService service;

    public OrganizadorController(OrganizadorService service) {
        this.service = service;
    }


//=========================== Métodos GET ========================
    @GetMapping
    public List<OrganizadorDTO> listarTodos() { return service.listarTodos();}
    @GetMapping("/{id}")
    public ResponseEntity<OrganizadorDTO> buscaPorId(@PathVariable Integer id)
    {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

//========================== Métodos POST ========================
    @PostMapping
    public ResponseEntity<OrganizadorDTO> cadastrar(@Valid @RequestBody OrganizadorCadastroDTO dto)
    {
        OrganizadorDTO organizador = service.cadastrar(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(organizador.usuario_id())
                .toUri();
        return ResponseEntity.created(location).body(organizador);
    }



//========================= DELETE ========================================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir (@PathVariable Integer id)
    {
        service.excluir(id);

        return ResponseEntity.noContent().build();
    }
}
