package br.edu.ufpel.mapacultural.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioCadastroDTO(

        @NotBlank(message = "O email é obrigatório")
        @Email(message = "Email inválido")
        @Size(max = 255)
        String email,

        @NotBlank(message = "O nome é obrigatório")
        @Size(max=100)
        String nome,

        @NotBlank()
        @Size(max=100)
        String sobrenome,

        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 8, message = "A senha deve possuir no mínimo 8 caractéres")
        String senha
) { }
