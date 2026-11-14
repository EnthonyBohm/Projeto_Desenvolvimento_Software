package br.edu.ufpel.mapacultural.dto;

public record ArtistaDTO(
        Integer usuario_id,
        String email,
        String nome,
        String sobrenome,
        String tipo_arte,
        String sub_genero,
        String descricao
) {
}
