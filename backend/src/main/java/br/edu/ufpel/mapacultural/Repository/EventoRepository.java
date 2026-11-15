package br.edu.ufpel.mapacultural.Repository;

import br.edu.ufpel.mapacultural.Model.Evento;
import br.edu.ufpel.mapacultural.Model.Local;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Integer> {

    boolean existsByNomeAndLocal(String nome,Local local);
}
