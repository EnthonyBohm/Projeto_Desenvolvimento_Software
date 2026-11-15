package br.edu.ufpel.mapacultural.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ArtistaCadastroDTO(
        @NotNull(message = "O ID do usuário é obrigatório")
        @Positive(message = "O ID deve ser positivo")
        Integer         usuario_id,

        @NotNull(message = "O Tipo de arte deve ser informado")
        @Size(max = 32, message = "Limite de 32 Caracteres")
        String          tipo_arte,

        @NotNull(message = "o Sub Genero deve ser informado")
        @Size(max = 64, message = "Limite de 64 Caracteres")
        String          sub_genero,

        String          descricao
) {
}
