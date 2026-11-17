package br.edu.ufpel.mapacultural.Service;

import br.edu.ufpel.mapacultural.Model.Artista;
import br.edu.ufpel.mapacultural.Model.UsuarioRegistrado;
import br.edu.ufpel.mapacultural.Repository.ArtistaRepository;
import br.edu.ufpel.mapacultural.Repository.UsuarioRepository;
import br.edu.ufpel.mapacultural.dto.ArtistaCadastroDTO;
import br.edu.ufpel.mapacultural.dto.ArtistaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistaService {

    private final ArtistaRepository artistaRepository;
    private final UsuarioRepository usuarioRepository;

    public ArtistaService(ArtistaRepository repository, ArtistaRepository artistaRepository, UsuarioRepository usuarioRepository) {
        this.artistaRepository = artistaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public ArtistaDTO convertoToDTO(Artista artista){
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

//========================= GET ========================
    @Transactional(readOnly = true)
    public List<ArtistaDTO> listarTodos()
    {
        return artistaRepository.findAll()
                .stream()
                .map(this::convertoToDTO)
                .toList();
    }
    @Transactional(readOnly = true)
    public Optional<ArtistaDTO> buscarPorId(Integer id)
    {
        return artistaRepository.findById(id)
                .map(this::convertoToDTO);
    }

//========================= POST ========================
    @Transactional
    public ArtistaDTO cadastrar (ArtistaCadastroDTO dto)
    {
        UsuarioRegistrado usuario = usuarioRepository
                .findById(dto.usuario_id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        if (artistaRepository.existsById(dto.usuario_id()))
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Este usuário já é um Artista");
        }

        Artista artista = new Artista();
        artista.setUsuario(usuario);
        artista.setTipo_arte(dto.tipo_arte());
        artista.setSub_genero(dto.sub_genero());
        artista.setDescricao(dto.descricao());

        Artista artista_salvo = artistaRepository.save(artista);

        return new ArtistaDTO (
                artista_salvo.getUsuario_id(),
                usuario.getEmail(),
                usuario.getNome(),
                usuario.getSobrenome(),
                artista_salvo.getTipo_arte(),
                artista_salvo.getSub_genero(),
                artista_salvo.getDescricao()
        );

    }



//========================= DELETE ========================
    @Transactional
    public void excluir (Integer id)
    {
       Artista artista = artistaRepository.findById(id)
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Artista não encontrado"));
        artistaRepository.delete(artista);
    }
}
