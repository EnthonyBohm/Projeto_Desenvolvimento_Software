package br.edu.ufpel.mapacultural.Service;

import br.edu.ufpel.mapacultural.Model.Local;
import br.edu.ufpel.mapacultural.Repository.EventoRepository;
import br.edu.ufpel.mapacultural.Repository.LocalRepository;
import br.edu.ufpel.mapacultural.dto.LocalCadastroDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class LocalService {

    private final LocalRepository localRepository;
    private final EventoRepository eventoRepository;

    public LocalService(LocalRepository localRepository, EventoRepository eventoRepository) {
        this.localRepository = localRepository;
        this.eventoRepository = eventoRepository;
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

        Local local_salvo = localRepository.save(local);

        return local_salvo;
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
