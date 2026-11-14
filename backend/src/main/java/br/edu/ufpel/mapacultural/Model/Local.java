package br.edu.ufpel.mapacultural.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Local {

    @Id
    @Column(name = "local_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer         local_id;

    private String          nome;
    private String          endereco;
    private Integer         capacidade;
}
