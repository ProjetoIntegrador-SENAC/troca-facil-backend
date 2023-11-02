package br.com.trocafacil.ems.domain.model.trade;

import br.com.trocafacil.ems.domain.helpers.enums.Status;
import br.com.trocafacil.ems.domain.model.product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity(name = "Trade")
@Table(name = "trades")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRADE_ID")
    private Long trade_id;

    @ManyToOne
    @NotNull
    private Product product_posted;

    @ManyToOne
    @NotNull
    private Product product_proposal;

    @Enumerated(EnumType.STRING)
    private Status status;
}
