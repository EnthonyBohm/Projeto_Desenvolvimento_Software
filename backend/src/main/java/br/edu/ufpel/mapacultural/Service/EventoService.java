package br.edu.ufpel.mapacultural.Service;


import br.edu.ufpel.mapacultural.Model.Evento;
import br.edu.ufpel.mapacultural.Model.Local;
import br.edu.ufpel.mapacultural.Model.Organizador;
import br.edu.ufpel.mapacultural.Repository.EventoRepository;
import br.edu.ufpel.mapacultural.Repository.LocalRepository;
import br.edu.ufpel.mapacultural.Repository.OrganizadorRepository;
import br.edu.ufpel.mapacultural.dto.EventoCadastroDTO;
import br.edu.ufpel.mapacultural.dto.EventoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    private final EventoRepository          eventoRepository;
    private final LocalRepository           localRepository;
    private final OrganizadorRepository     organizadorRepository;

    public EventoService(EventoRepository eventoRepository, LocalRepository localRepository, OrganizadorRepository organizadorRepository) {
        this.eventoRepository = eventoRepository;
        this.localRepository = localRepository;
        this.organizadorRepository = organizadorRepository;
    }


    public EventoDTO convertToDTO (Evento evento) {
        return new EventoDTO(
                evento.getEventoId(),
                evento.getNome(),
                evento.getData_evento(),
                evento.getStatus_evento(),
                evento.getDescricao(),
                evento.getOrganizador().getUsuario_id(),
                evento.getOrganizador().getUsuario().getNome(),
                evento.getOrganizador().getUsuario().getSobrenome(),
                evento.getOrganizador().getUsuario().getEmail(),
                evento.getLocal().getLocalId(),
                evento.getLocal().getNome(),
                evento.getLocal().getEndereco(),
                evento.getLocal().getCapacidade(),
                evento.getLocal().getLatitude(),
                evento.getLocal().getLongitude()
        );
    }

//======================    GET     ========================
    @Transactional(readOnly = true)
    public List<EventoDTO> listarTodos()
    {
        return eventoRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }
    @Transactional(readOnly = true)
    public Optional<EventoDTO> buscaPorId(Integer id)
    {
        return eventoRepository.findById(id)
                .map(this::convertToDTO);
    }
    @Transactional(readOnly = true)
    public List<EventoDTO> listarPorLocal(Integer localId)
    {
        return eventoRepository.findByLocalLocalId(localId)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }
    @Transactional(readOnly = true)
    public Optional<EventoDTO> buscarPorEvento(Integer localId, Integer eventoId)
    {
        return eventoRepository
                .findByLocalLocalIdAndEventoId(localId, eventoId)
                .map(this::convertToDTO);
    }
//======================    POST     ========================
    @Transactional
    public EventoDTO cadastrar (EventoCadastroDTO dto)
    {
        Local local = localRepository
                .findById(dto.localId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Local não encontrado"));
        Organizador organizador = organizadorRepository
                .findById(dto.organizadorId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Organizador não encontrado"));
        if (eventoRepository.existsByNomeAndLocal(dto.nome(), local))
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Evento já existe");
        }

        Evento evento = new Evento();
        evento.setNome(dto.nome());
        evento.setData_evento(dto.dataEvento());
        evento.setStatus_evento(dto.statusEvento());
        evento.setDescricao(dto.descricao());
        evento.setLocal(local);
        evento.setOrganizador(organizador);

        Evento evento_salvo = eventoRepository.save(evento);

        return convertToDTO(evento);
    }

//========================= DELETE ========================
    @Transactional
    public void excluir (Integer id)
    {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Organizador não encontrado"));

        eventoRepository.delete(evento);
    }
}
