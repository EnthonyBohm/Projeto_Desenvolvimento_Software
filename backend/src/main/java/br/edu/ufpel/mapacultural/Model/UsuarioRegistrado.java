package br.edu.ufpel.mapacultural.Model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "usuario_registrado")
@Getter @Setter @NoArgsConstructor
public class UsuarioRegistrado {

    @Id
    @Column(name = "usuario_id")
    @ToString.Exclude
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer usuarioId;

    private String email;
    private String nome;
    private String sobrenome;

    @ToString.Exclude
    @Column(name = "senha_hash")
    private String senhaHash;

}
