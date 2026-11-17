package br.edu.ufpel.mapacultural.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "artista")
@Getter @Setter @NoArgsConstructor
public class Artista {

    @Id
    private Integer usuario_id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "usuario_id")
    private         UsuarioRegistrado usuario;

    private         String tipo_arte;
    private         String sub_genero;
    private         String descricao;

    // tabela participa_evento: eventos de que este artista participa
    @ManyToMany
    @JoinTable(name = "participa_evento",
            joinColumns = @JoinColumn(name = "artista_id"),
            inverseJoinColumns = @JoinColumn(name = "evento_id"))
    private Set<Evento> eventosParticipados = new HashSet<>();

    public String toString() {
        return String.format("%s é um %s de genero %s que %s", usuario.getNome(), this.tipo_arte, this.sub_genero, this.descricao);
    }
}