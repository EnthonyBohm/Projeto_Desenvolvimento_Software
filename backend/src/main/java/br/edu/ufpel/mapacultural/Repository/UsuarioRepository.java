package br.edu.ufpel.mapacultural.Repository;

import br.edu.ufpel.mapacultural.Model.UsuarioRegistrado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioRegistrado, Integer> {



}

// Herança de JpaRepo... (não precisa implementar):
//      Método	            Função
//      findAll()	        Busca todos os registros
//      findById(id)	    Busca um registro pelo ID
//      save(objeto)	    Insere ou atualiza
//      deleteById(id)	    Exclui pelo ID
//      count()	            Conta os registros