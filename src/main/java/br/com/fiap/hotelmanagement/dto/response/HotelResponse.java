package br.com.fiap.hotelmanagement.dto.response;

import lombok.Builder;

@Builder
public record HotelResponse(

        Long id,

        String nome,

        String endereco,

        String descricao,

        Double avaliacao
) {
}
