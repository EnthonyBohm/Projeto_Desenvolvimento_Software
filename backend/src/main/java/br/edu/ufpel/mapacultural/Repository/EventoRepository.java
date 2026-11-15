package br.edu.ufpel.mapacultural.Repository;

import br.edu.ufpel.mapacultural.Model.Evento;
import br.edu.ufpel.mapacultural.Model.Local;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventoRepository extends JpaRepository<Evento, Integer> {

    boolean existsByNomeAndLocal(String nome,Local local);

    List<Evento> findByLocalLocalId(Integer localLocalId);

    Optional<Evento> findByLocalLocalIdAndEventoId(Integer localLocalId, Integer eventoId);
}
