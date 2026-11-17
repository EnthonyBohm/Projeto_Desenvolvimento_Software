package br.edu.ufpel.mapacultural.Controller;

import br.edu.ufpel.mapacultural.Model.UsuarioRegistrado;
import br.edu.ufpel.mapacultural.Repository.UsuarioRepository;
import br.edu.ufpel.mapacultural.Service.UsuarioService;
import br.edu.ufpel.mapacultural.dto.UsuarioCadastroDTO;
import br.edu.ufpel.mapacultural.dto.UsuarioDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    //=======================    GET     ========================
    @GetMapping
    public List<UsuarioDTO> listarTodos()
    {
        return service.listarTodos();
    }
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable("id") Integer id)
    {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //=======================    POST     ========================
    @PostMapping
    public ResponseEntity<UsuarioDTO> cadastrar ( @Valid @RequestBody UsuarioCadastroDTO dto )
    {
        UsuarioDTO usuario = service.cadastrar(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(usuario.usuario_id())
                .toUri();
        return ResponseEntity.created(location).body(usuario);
    }

    //=======================    DELETE     ========================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Integer id)
    {
        service.excluir(id);

        return ResponseEntity.noContent().build();
    }
}
