package br.edu.ufpel.mapacultural.Controller;


import br.edu.ufpel.mapacultural.Model.Organizador;
import br.edu.ufpel.mapacultural.Repository.OrganizadorRepository;
import br.edu.ufpel.mapacultural.Service.OrganizadorService;
import br.edu.ufpel.mapacultural.dto.OrganizadorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/organizadores")
public class OrganizadorController {

    private final OrganizadorService service;

    public OrganizadorController(OrganizadorService service) {
        this.service = service;
    }

    @GetMapping
    public List<OrganizadorDTO> listarTodos() { return service.listarTodos();}

    @GetMapping("/{id}")
    public ResponseEntity<OrganizadorDTO> buscaPorId(@PathVariable Integer id){
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
