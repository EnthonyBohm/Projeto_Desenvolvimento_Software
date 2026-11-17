package br.edu.ufpel.mapacultural.Service;

import br.edu.ufpel.mapacultural.Model.Artista;
import br.edu.ufpel.mapacultural.Model.Evento;
import br.edu.ufpel.mapacultural.Repository.ArtistaRepository;
import br.edu.ufpel.mapacultural.Repository.EventoRepository;
import br.edu.ufpel.mapacultural.Repository.UsuarioRepository;
import br.edu.ufpel.mapacultural.dto.ArtistaDTO;
import br.edu.ufpel.mapacultural.dto.UsuarioDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EventoVinculoService {

    private final EventoRepository  eventoRepository;
    private final ArtistaRepository artistaRepository;
    private final UsuarioRepository usuarioRepository;
    private final ArtistaService    artistaService;
    private final UsuarioService    usuarioService;

    public EventoVinculoService(EventoRepository eventoRepository,
                                ArtistaRepository artistaRepository,
                                UsuarioRepository usuarioRepository,
                                ArtistaService artistaService,
                                UsuarioService usuarioService) {
        this.eventoRepository = eventoRepository;
        this.artistaRepository = artistaRepository;
        this.usuarioRepository = usuarioRepository;
        this.artistaService = artistaService;
        this.usuarioService = usuarioService;
    }

    private Evento buscarEvento(Integer id) {
        return eventoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento não encontrado"));
    }
    private Artista buscarArtista(Integer id) {
        return artistaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Artista não encontrado"));
    }

    // ========================= INSCRICAO (lado do evento) =========================
    @Transactional(readOnly = true)
    public List<UsuarioDTO> listarInscritos(Integer eventoId) {
        buscarEvento(eventoId); // garante o 404 se o evento não existe
        return usuarioRepository.buscarInscritosPorEvento(eventoId)
                .stream()
                .map(usuarioService::convertToDTO)
                .toList();
    }

    // ========================= PARTICIPA_EVENTO =========================
    @Transactional(readOnly = true)
    public List<ArtistaDTO> listarArtistas(Integer eventoId) {
        buscarEvento(eventoId);
        return artistaRepository.buscarPorEvento(eventoId)
                .stream()
                .map(artistaService::convertoToDTO)
                .toList();
    }

    @Transactional
    public void vincularArtista(Integer eventoId, Integer artistaId) {
        Evento evento = buscarEvento(eventoId);
        Artista artista = buscarArtista(artistaId);

        if (!artista.getEventosParticipados().add(evento)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Artista já vinculado a este evento");
        }
    }

    @Transactional
    public void desvincularArtista(Integer eventoId, Integer artistaId) {
        Evento evento = buscarEvento(eventoId);
        Artista artista = buscarArtista(artistaId);

        if (!artista.getEventosParticipados().remove(evento)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Artista não está vinculado a este evento");
        }
    }
}