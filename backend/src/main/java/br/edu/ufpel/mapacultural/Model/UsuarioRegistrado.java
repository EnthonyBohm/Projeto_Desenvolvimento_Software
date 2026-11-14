package br.edu.ufpel.mapacultural.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


@Entity
@Table(name = "usuario_registrado")
@Getter @Setter @NoArgsConstructor
public class UsuarioRegistrado {

    @Id
    @Column(name = "usuario_id")
    @ToString.Exclude
    private Integer usuarioId;

    private String email;
    private String nome;
    private String sobrenome;

    @ToString.Exclude
    @Column(name = "senha_hash")
    private String senhaHash;

}
