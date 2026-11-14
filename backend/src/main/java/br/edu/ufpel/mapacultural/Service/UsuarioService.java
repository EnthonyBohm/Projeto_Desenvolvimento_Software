package br.edu.ufpel.mapacultural.Service;

import br.edu.ufpel.mapacultural.Model.UsuarioRegistrado;
import br.edu.ufpel.mapacultural.Repository.UsuarioRepository;
import br.edu.ufpel.mapacultural.dto.UsuarioDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    private UsuarioDTO convertToDTO(UsuarioRegistrado usuario){
        return new UsuarioDTO(
                usuario.getUsuarioId(),
                usuario.getEmail(),
                usuario.getNome(),
                usuario.getSobrenome()
        );
    }

    @Transactional (readOnly = true)
    public List<UsuarioDTO> listarTodos(){
        return repository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Transactional(readOnly=true)
    public Optional<UsuarioDTO> buscarPorId(Integer id) {
        return repository.findById(id)
                .map(this::convertToDTO);
    }

}
