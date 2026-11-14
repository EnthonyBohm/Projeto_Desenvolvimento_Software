package br.edu.ufpel.mapacultural.dto;

public record UsuarioDTO(
        Integer usuario_id,
        String email,
        String nome,
        String sobrenome
) {
}
