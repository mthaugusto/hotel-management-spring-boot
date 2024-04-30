package br.com.fiap.hotelmanagement.dto.request;

import br.com.fiap.hotelmanagement.entity.TipoQuarto;

public record QuartoRequest(

        String numeroQuarto,

        Double precoPorNoite,

        TipoQuarto tipoQuarto,

        Long hotelId

) {
}
