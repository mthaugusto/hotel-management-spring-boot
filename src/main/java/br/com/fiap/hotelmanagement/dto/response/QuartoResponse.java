package br.com.fiap.hotelmanagement.dto.response;

import br.com.fiap.hotelmanagement.entity.Hotel;
import br.com.fiap.hotelmanagement.entity.TipoQuarto;
import lombok.Builder;

@Builder
public record QuartoResponse(
        Long id,

        String numeroQuarto,

        Double precoPorNoite,

        TipoQuarto tipoQuarto,

        HotelResponse hotel
) {
}
