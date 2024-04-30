package br.com.fiap.hotelmanagement.dto.request;

import jakarta.persistence.Column;

public record HotelRequest(

        String nome,

        String endereco,

        String descricao,

        Double avaliacao
) {
}
