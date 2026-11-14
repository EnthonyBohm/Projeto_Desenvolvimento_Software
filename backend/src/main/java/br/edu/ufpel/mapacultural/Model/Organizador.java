package br.edu.ufpel.mapacultural.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter@Setter@NoArgsConstructor
@Table(name = "organizador")
public class Organizador {

    @Id
    private Integer usuario_id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "usuario_id")
    private UsuarioRegistrado usuario;

}
