package br.edu.ufpel.mapacultural.Repository;

import br.edu.ufpel.mapacultural.Model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Integer> {
}
