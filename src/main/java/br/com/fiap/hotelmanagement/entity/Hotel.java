package br.com.fiap.hotelmanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="TB_HOTEL")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_HOTEL")
    @SequenceGenerator(name="SQ_HOTEL", sequenceName = "SQ_HOTEL", allocationSize = 1)
    @Column(name="ID_HOTEL")
    private Long id;

    @Column(name="NM_HOTEL")
    private String nome;

    @Column(name="END_HOTEL")
    private String endereco;

    @Column(name="DS_HOTEL")
    private String descricao;

    @Column(name="AVALIACAO_HOTEL")
    private Double avaliacao;

}
