package br.edu.ufpel.mapacultural.Service;

import br.edu.ufpel.mapacultural.Model.Artista;
import br.edu.ufpel.mapacultural.Repository.ArtistaRepository;
import br.edu.ufpel.mapacultural.dto.ArtistaDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistaService {

    private final ArtistaRepository repository;

    public ArtistaService(ArtistaRepository repository) {
        this.repository = repository;
    }

    private ArtistaDTO convertoToDTO(Artista artista){
        return new ArtistaDTO(
                artista.getUsuario().getUsuarioId(),
                artista.getUsuario().getEmail(),
                artista.getUsuario().getNome(),
                artista.getUsuario().getSobrenome(),
                artista.getTipo_arte(),
                artista.getSub_genero(),
                artista.getDescricao()
        );
    }

    @Transactional(readOnly = true)
    public List<ArtistaDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::convertoToDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public Optional<ArtistaDTO> buscarPorId(Integer id) {
        return repository.findById(id)
                .map(this::convertoToDTO);
    }
}
