package br.edu.ufpel.mapacultural.Repository;

import br.edu.ufpel.mapacultural.Model.Artista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArtistaRepository extends JpaRepository<Artista, Integer> {

    // artistas que participam de um evento
    @Query("select a from Artista a join a.eventosParticipados e where e.eventoId = :eventoId")
    List<Artista> buscarPorEvento(@Param("eventoId") Integer eventoId);

}