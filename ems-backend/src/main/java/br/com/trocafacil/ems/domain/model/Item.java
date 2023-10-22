package br.com.trocafacil.ems.domain.model;

import br.com.trocafacil.ems.apps.trade.enums.Condition;
import br.com.trocafacil.ems.apps.trade.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "item")
@Table(name = "items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String account_id;

    @NotNull
    private String product;

    @NotNull
    private BigDecimal price;

    @Size(min = 0, max = 50)
    private int amount;

    @NotNull
    private String curCondition;

    private String urlImages;
    private String status;
    private String category;
    private String subCategory;
    private String cep;

}
