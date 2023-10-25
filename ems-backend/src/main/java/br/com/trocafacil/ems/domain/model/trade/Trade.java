package br.com.trocafacil.ems.domain.model.trade;

import jakarta.persistence.*;
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
    private long id;
    private long id_product_posted_id;
    private long id_product_proposal;
    private String status;
}
