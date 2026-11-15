package br.edu.ufpel.mapacultural.Service;

import br.edu.ufpel.mapacultural.Model.Organizador;
import br.edu.ufpel.mapacultural.Model.UsuarioRegistrado;
import br.edu.ufpel.mapacultural.Repository.OrganizadorRepository;
import br.edu.ufpel.mapacultural.Repository.UsuarioRepository;
import br.edu.ufpel.mapacultural.dto.OrganizadorCadastroDTO;
import br.edu.ufpel.mapacultural.dto.OrganizadorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizadorService {

    private final OrganizadorRepository     organizadorRepository;
    private final UsuarioRepository         usuarioRepository;

    public OrganizadorService(OrganizadorRepository organizadorRepository, UsuarioRepository usuarioRepository) {
        this.organizadorRepository = organizadorRepository;
        this.usuarioRepository = usuarioRepository;
    }

    private OrganizadorDTO convertToDTO (Organizador organizador)
    {
        return new OrganizadorDTO(
                organizador.getUsuario().getUsuarioId(),
                organizador.getUsuario().getEmail(),
                organizador.getUsuario().getNome(),
                organizador.getUsuario().getSobrenome()
        );
    }


//    ================= Métodos GET ========================

    @Transactional (readOnly = true)
    public List<OrganizadorDTO> listarTodos()
    {
        return organizadorRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }
    @Transactional (readOnly = true)
    public Optional<OrganizadorDTO> buscarPorId(Integer id)
    {
        return organizadorRepository.findById(id)
                .map(this::convertToDTO);
    }



//    ================= Métodos POST ========================
    @Transactional
    public OrganizadorDTO cadastrar (OrganizadorCadastroDTO dto)
    {
        UsuarioRegistrado usuario = usuarioRepository
                .findById(dto.usuario_id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        if (organizadorRepository.existsById(dto.usuario_id()))
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Este usuário já é um organizador");
        }

        Organizador organizador = new Organizador();
        organizador.setUsuario(usuario);

        Organizador organizador_salvo = organizadorRepository.save(organizador);

        return new OrganizadorDTO (
                organizador_salvo.getUsuario_id(),
                usuario.getEmail(),
                usuario.getNome(),
                usuario.getSobrenome()
        );
    }




//========================= DELETE ========================
    @Transactional
    public void excluir (Integer id)
    {
        Organizador organizador = organizadorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Organizador não encontrado"));

        organizadorRepository.delete(organizador);
    }
}
