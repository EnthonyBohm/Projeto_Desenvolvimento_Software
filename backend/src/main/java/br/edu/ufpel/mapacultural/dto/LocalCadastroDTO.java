package br.edu.ufpel.mapacultural.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record LocalCadastroDTO(
        @NotBlank(message = "Nome do local não pode ser vázio")
        @Size(max = 100)
        String              nome,

        @NotBlank(message = "Endereco não pode ser vázio")
        @Size(max = 255)
        String              endereco,

        @NotNull(message = "Capacidade deve ser informada")
        @Positive(message = "Apenas valores positivos de capacidade máxima devem ser aceitos")
        Integer             capacidade

) { }
