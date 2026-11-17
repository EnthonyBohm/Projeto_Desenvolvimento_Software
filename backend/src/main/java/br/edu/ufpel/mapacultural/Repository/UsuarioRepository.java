package br.edu.ufpel.mapacultural.Repository;

import br.edu.ufpel.mapacultural.Model.UsuarioRegistrado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<UsuarioRegistrado, Integer> {

    boolean existsByEmail(String email);

    // usuários que confirmaram presença em um evento (sentido inverso da inscricao)
    @Query("select u from UsuarioRegistrado u join u.eventosConfirmados e where e.eventoId = :eventoId")
    List<UsuarioRegistrado> buscarInscritosPorEvento(@Param("eventoId") Integer eventoId);

}

// Herança de JpaRepo... (não precisa implementar):
//      Método	            Função
//      findAll()	        Busca todos os registros
//      findById(id)	    Busca um registro pelo ID
//      save(objeto)	    Insere ou atualiza
//      deleteById(id)	    Exclui pelo ID
//      count()	            Conta os registros