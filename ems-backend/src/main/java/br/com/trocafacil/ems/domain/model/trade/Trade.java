package br.com.trocafacil.ems.domain.model.trade;

import br.com.trocafacil.ems.domain.helpers.enums.Status;
import br.com.trocafacil.ems.domain.model.product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

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
    private Long id;

    @Temporal(TemporalType.DATE)
    private LocalDate createDate;

    @Temporal(TemporalType.DATE)
    private LocalDate settleDate;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "product_posted_id")
    private Product productPosted;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "product_proposal_id")
    private Product productProposal;

    @Enumerated(EnumType.STRING)
    private Status status;
}
