package br.edu.ufpel.mapacultural.dto;

import java.time.LocalDate;

public record EventoDTO(
        Integer         id,
        String          nome,
        LocalDate       data_evento,
        String          status_evento,
        String          descricao,
        Integer         organizador_id,
        String          organizador_nome,
        String          organizador_sobrenome,
        String          organizador_email,
        Integer         local_id,
        String          local_nome,
        String          local_endereco,
        Integer         local_capacidade
) {
}
