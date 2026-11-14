package br.edu.ufpel.mapacultural.Repository;

import br.edu.ufpel.mapacultural.Model.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistaRepository extends JpaRepository<Artista, Integer> {
}
