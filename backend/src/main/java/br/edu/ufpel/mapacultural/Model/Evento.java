package br.edu.ufpel.mapacultural.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Table(name = "evento")
public class Evento {

    @Id
    @Column ( name = "evento_id")
    private Integer             evento_id;

    private String              nome;
    private LocalDate           data_evento;
    private String              status_evento;
    private String              descricao;

    @ManyToOne
    @JoinColumn(name = "local_id")
    private Local local;

    @ManyToOne
    @JoinColumn(name = "organizador_id")
    private Organizador organizador;

}
