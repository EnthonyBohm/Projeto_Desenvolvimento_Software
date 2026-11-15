package br.edu.ufpel.mapacultural.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record EventoCadastroDTO(


        @NotBlank(message = "O nome é obrigatório")
        @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
        String nome,

        @NotNull(message = "A data do evento é obrigatória")
        @FutureOrPresent(message = "A data não pode ser anterior à data atual")
        LocalDate dataEvento,

        @Pattern(
                regexp = "ativo|cancelado|encerrado",
                message = "Status inválido"
        )
        String statusEvento,

        String descricao,

        @NotNull(message = "O local é obrigatório")
        Integer localId,

        @NotNull(message = "O organizador é obrigatório")
        Integer organizadorId


) { }
