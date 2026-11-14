package br.edu.ufpel.mapacultural.Service;


import br.edu.ufpel.mapacultural.Model.Evento;
import br.edu.ufpel.mapacultural.Repository.EventoRepository;
import br.edu.ufpel.mapacultural.dto.EventoDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    private final EventoRepository repository;

    public EventoService(EventoRepository repository) {
        this.repository = repository;
    }

    private EventoDTO convertToDTO (Evento evento) {
        return new EventoDTO(
                evento.getEvento_id(),
                evento.getNome(),
                evento.getData_evento(),
                evento.getStatus_evento(),
                evento.getDescricao(),
                evento.getOrganizador().getUsuario_id(),
                evento.getOrganizador().getUsuario().getNome(),
                evento.getOrganizador().getUsuario().getSobrenome(),
                evento.getOrganizador().getUsuario().getEmail(),
                evento.getLocal().getLocal_id(),
                evento.getLocal().getNome(),
                evento.getLocal().getEndereco(),
                evento.getLocal().getCapacidade()
        );
    }

    @Transactional(readOnly = true)
    public List<EventoDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public Optional<EventoDTO> buscaPorId(Integer id) {
        return repository.findById(id)
                .map(this::convertToDTO);
    }
}
