package br.edu.ufpel.mapacultural.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Local {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "local_id")
    private Integer localId;

    private String          nome;
    private String          endereco;
    private Integer         capacidade;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;
}
