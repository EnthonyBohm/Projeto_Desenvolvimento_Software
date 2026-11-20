package br.edu.ufpel.mapacultural.Service;

import br.edu.ufpel.mapacultural.Model.Local;
import br.edu.ufpel.mapacultural.Repository.EventoRepository;
import br.edu.ufpel.mapacultural.Repository.LocalRepository;
import br.edu.ufpel.mapacultural.dto.LocalCadastroDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class LocalService {

    private final LocalRepository localRepository;
    private final EventoRepository eventoRepository;
    private final GeocodingService geocodingService;

    public LocalService(LocalRepository localRepository, EventoRepository eventoRepository, GeocodingService geocodingService) {
        this.localRepository = localRepository;
        this.eventoRepository = eventoRepository;
        this.geocodingService = geocodingService;
    }

    //===========================       GET       ========================
    @Transactional(readOnly = true)
    public List<Local> listarTodos() {
        return localRepository.findAll();
    }
    @Transactional(readOnly = true)
    public Optional<Local> buscarPorId(Integer id) {
        return localRepository.findById(id);
    }

//===========================       POST       ========================
    @Transactional
    public Local cadastrar (LocalCadastroDTO dto)
    {
//        if (repository.existsByNomeAndEndereco(dto.nome(), dto.endereco()))
//        {
//           throw new ResponseStatusException(HttpStatus.CONFLICT, "Local já existe");
//        }

        localRepository.existsByNomeAndEndereco(dto.nome(), dto.endereco());

//        repository.existsByNome(dto.nome());

        Local local = new Local();
        local.setNome(dto.nome());
        local.setEndereco(dto.endereco());
        local.setCapacidade(dto.capacidade());

        try {
            Optional<GeocodingService.Coordinates> coordenadas =
                    geocodingService.buscarCoordenadas(dto.endereco());

            coordenadas.ifPresent(coord -> {
                local.setLatitude(coord.latitude());
                local.setLongitude(coord.longitude());
            });

        } catch (RestClientException e) {
            System.err.println("Erro ao buscar coordenadas: " + e.getMessage());
        }

        return localRepository.save(local);

    }


//========================= DELETE ========================
    @Transactional
    public void excluir (Integer id)
    {
        Local local = localRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Organizador não encontrado"));

        localRepository.delete(local);
    }
}