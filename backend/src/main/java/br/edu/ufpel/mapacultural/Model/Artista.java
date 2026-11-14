package br.edu.ufpel.mapacultural.Model;

import jakarta.persistence.*;
import lombok.*;

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

    public String toString() {
        return String.format("%s é um %s de genero %s que %s", usuario.getNome(), this.tipo_arte, this.sub_genero, this.descricao);
    }
}
