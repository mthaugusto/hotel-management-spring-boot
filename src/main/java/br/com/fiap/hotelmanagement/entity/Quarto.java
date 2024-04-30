package br.com.fiap.hotelmanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name="TB_QUARTO")
@Entity
public class Quarto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_QUARTO")
    @SequenceGenerator(name="SQ_QUARTO", sequenceName = "SQ_QUARTO", allocationSize = 1)
    @Column(name="ID_QUARTO")
    private Long id;

    private String numeroQuarto;

    @Enumerated(EnumType.STRING)
    private TipoQuarto tipoQuarto;

    private Double precoPorNoite;

    private boolean disponivel;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "HOTEL", referencedColumnName = "ID_HOTEL",
            foreignKey = @ForeignKey(name = "FK_HOTEL_QUARTO"))
    private Hotel hotel;
}
