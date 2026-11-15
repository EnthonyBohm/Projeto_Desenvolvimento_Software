package br.edu.ufpel.mapacultural.Service;

import br.edu.ufpel.mapacultural.Model.Local;
import br.edu.ufpel.mapacultural.Repository.LocalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LocalService {

    private final LocalRepository repository;

    public LocalService(LocalRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Local> listarTodos() {
        return repository.findAll();
    }
    @Transactional(readOnly = true)
    public Optional<Local> buscarPorId(Integer id) {
        return repository.findById(id);
    }

}
