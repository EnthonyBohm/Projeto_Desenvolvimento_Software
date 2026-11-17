package br.edu.ufpel.mapacultural.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


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

    // tabela inscricao: eventos em que o usuário confirmou presença
    @ManyToMany
    @JoinTable(name = "inscricao",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "evento_id"))
    private Set<Evento> eventosConfirmados = new HashSet<>();

    // tabela seguindo_artista
    @ManyToMany
    @JoinTable(name = "seguindo_artista",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "artista_id"))
    private Set<Artista> artistasSeguidos = new HashSet<>();

    // tabela seguindo_local
    @ManyToMany
    @JoinTable(name = "seguindo_local",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "local_id"))
    private Set<Local> locaisSeguidos = new HashSet<>();

}