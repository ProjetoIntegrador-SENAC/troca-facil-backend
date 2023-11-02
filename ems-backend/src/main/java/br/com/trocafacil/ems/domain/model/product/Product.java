package br.com.trocafacil.ems.domain.model.product;

import br.com.trocafacil.ems.domain.model.trade.Trade;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;

@Entity(name = "product")
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String account_id;

    @NotNull
    private String name;

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

    @OneToMany(mappedBy = "product_posted")
//    @JoinColumn(name = "product_posted", referencedColumnName ="trade_id")
    private ArrayList<Trade> trades;

}
