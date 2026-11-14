package br.edu.ufpel.mapacultural.Service;

import br.edu.ufpel.mapacultural.Model.Organizador;
import br.edu.ufpel.mapacultural.Repository.OrganizadorRepository;
import br.edu.ufpel.mapacultural.dto.OrganizadorDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizadorService {

    private final OrganizadorRepository repository;

    public OrganizadorService(OrganizadorRepository repository) {
        this.repository = repository;
    }

    private OrganizadorDTO convertToDTO (Organizador organizador) {
        return new OrganizadorDTO(
                organizador.getUsuario().getUsuarioId(),
                organizador.getUsuario().getEmail(),
                organizador.getUsuario().getNome(),
                organizador.getUsuario().getSobrenome()
        );
    }

    @Transactional (readOnly = true)
    public List<OrganizadorDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Transactional (readOnly = true)
    public Optional<OrganizadorDTO> buscarPorId(Integer id) {
        return repository.findById(id)
                .map(this::convertToDTO);
    }
}
