package br.com.fiap.hotelmanagement.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record HotelRequest(

        @NotBlank(message = "O nome não pode ser vazio.")
        @Size(min = 2, max = 100, message = "O nome do hotel deve ter entre 2 e 100 caracteres.")
        String nome,

        @NotBlank(message = "O endereço do hotel não pode ser vazio.")
        @Size(min = 2, max = 255, message = "O endereço do hotel deve ter entre 2 e 255 caracteres.")
        String endereco,

        @NotBlank(message = "A descrição do hotel não pode ser vazia.")
        @Size(min = 2, max = 1000, message = "A descrição do hotel deve ter entre 2 e 1000 caracteres.")
        String descricao,

        @NotNull(message = "A avaliação do hotel não pode ser vazia.")
        Double avaliacao
) {
}
