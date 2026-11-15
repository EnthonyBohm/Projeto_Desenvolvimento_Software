package br.edu.ufpel.mapacultural.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrganizadorCadastroDTO(

        @NotNull(message = "O ID do usuário é obrigatório")
        @Positive(message = "O ID deve ser positivo")
        Integer usuario_id
) { }
