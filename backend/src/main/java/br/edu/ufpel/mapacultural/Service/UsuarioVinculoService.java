package br.edu.ufpel.mapacultural.Service;

import br.edu.ufpel.mapacultural.Model.Artista;
import br.edu.ufpel.mapacultural.Model.Evento;
import br.edu.ufpel.mapacultural.Model.Local;
import br.edu.ufpel.mapacultural.Model.UsuarioRegistrado;
import br.edu.ufpel.mapacultural.Repository.ArtistaRepository;
import br.edu.ufpel.mapacultural.Repository.EventoRepository;
import br.edu.ufpel.mapacultural.Repository.LocalRepository;
import br.edu.ufpel.mapacultural.Repository.UsuarioRepository;
import br.edu.ufpel.mapacultural.dto.ArtistaDTO;
import br.edu.ufpel.mapacultural.dto.EventoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioVinculoService {

    private final UsuarioRepository usuarioRepository;
    private final EventoRepository  eventoRepository;
    private final ArtistaRepository artistaRepository;
    private final LocalRepository   localRepository;
    private final EventoService     eventoService;
    private final ArtistaService    artistaService;

    public UsuarioVinculoService(UsuarioRepository usuarioRepository,
                                 EventoRepository eventoRepository,
                                 ArtistaRepository artistaRepository,
                                 LocalRepository localRepository,
                                 EventoService eventoService,
                                 ArtistaService artistaService) {
        this.usuarioRepository = usuarioRepository;
        this.eventoRepository = eventoRepository;
        this.artistaRepository = artistaRepository;
        this.localRepository = localRepository;
        this.eventoService = eventoService;
        this.artistaService = artistaService;
    }

    // ---------- buscas auxiliares (404 se não existir) ----------
    private UsuarioRegistrado buscarUsuario(Integer id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
    }
    private Evento buscarEvento(Integer id) {
        return eventoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento não encontrado"));
    }
    private Artista buscarArtista(Integer id) {
        return artistaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Artista não encontrado"));
    }
    private Local buscarLocal(Integer id) {
        return localRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Local não encontrado"));
    }

    // ========================= INSCRICAO =========================
    @Transactional(readOnly = true)
    public List<EventoDTO> listarEventosConfirmados(Integer usuarioId) {
        return buscarUsuario(usuarioId).getEventosConfirmados()
                .stream()
                .map(eventoService::convertToDTO)
                .toList();
    }

    @Transactional
    public void confirmarPresenca(Integer usuarioId, Integer eventoId) {
        UsuarioRegistrado usuario = buscarUsuario(usuarioId);
        Evento evento = buscarEvento(eventoId);

        if (!"ativo".equals(evento.getStatus_evento()) || evento.getData_evento().isBefore(LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Este evento não aceita confirmação de presença");
        }
        if (!usuario.getEventosConfirmados().add(evento)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Presença já confirmada neste evento");
        }
    }

    @Transactional
    public void cancelarPresenca(Integer usuarioId, Integer eventoId) {
        UsuarioRegistrado usuario = buscarUsuario(usuarioId);
        Evento evento = buscarEvento(eventoId);

        if (!usuario.getEventosConfirmados().remove(evento)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Presença não encontrada neste evento");
        }
    }

    // ========================= SEGUINDO_ARTISTA =========================
    @Transactional(readOnly = true)
    public List<ArtistaDTO> listarArtistasSeguidos(Integer usuarioId) {
        return buscarUsuario(usuarioId).getArtistasSeguidos()
                .stream()
                .map(artistaService::convertoToDTO)
                .toList();
    }

    @Transactional
    public void seguirArtista(Integer usuarioId, Integer artistaId) {
        UsuarioRegistrado usuario = buscarUsuario(usuarioId);
        Artista artista = buscarArtista(artistaId);

        if (!usuario.getArtistasSeguidos().add(artista)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Você já segue este artista");
        }
    }

    @Transactional
    public void deixarDeSeguirArtista(Integer usuarioId, Integer artistaId) {
        UsuarioRegistrado usuario = buscarUsuario(usuarioId);
        Artista artista = buscarArtista(artistaId);

        if (!usuario.getArtistasSeguidos().remove(artista)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Você não segue este artista");
        }
    }

    // ========================= SEGUINDO_LOCAL =========================
    @Transactional(readOnly = true)
    public List<Local> listarLocaisSeguidos(Integer usuarioId) {
        return new ArrayList<>(buscarUsuario(usuarioId).getLocaisSeguidos());
    }

    @Transactional
    public void seguirLocal(Integer usuarioId, Integer localId) {
        UsuarioRegistrado usuario = buscarUsuario(usuarioId);
        Local local = buscarLocal(localId);

        if (!usuario.getLocaisSeguidos().add(local)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Você já segue este local");
        }
    }

    @Transactional
    public void deixarDeSeguirLocal(Integer usuarioId, Integer localId) {
        UsuarioRegistrado usuario = buscarUsuario(usuarioId);
        Local local = buscarLocal(localId);

        if (!usuario.getLocaisSeguidos().remove(local)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Você não segue este local");
        }
    }
}