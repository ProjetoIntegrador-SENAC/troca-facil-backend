package br.com.trocafacil.ems.domain.model.product;

import br.com.trocafacil.ems.domain.helpers.enums.ProductCondition;
import br.com.trocafacil.ems.domain.helpers.enums.ProductStatus;
import br.com.trocafacil.ems.domain.model.trade.Trade;
import br.com.trocafacil.ems.domain.model.account.Account;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

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
    @ManyToOne
    @JoinColumn(name = "account_id", insertable = false, updatable = false)
    private Account account;

    @NotNull
    private String name;

    @NotNull
    private BigDecimal price;

    @Size(min = 0, max = 50)
    private int amount;

    @Temporal(TemporalType.DATE)
    private Date date;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ProductCondition curCondition;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "sub_category_id")
    private SubCategory subCategory;

    @OneToMany(mappedBy = "product_posted")
    private ArrayList<Trade> trades_posted;

//    @OneToMany(mappedBy = "product_posted")
//    private ArrayList<Trade> trades;

}
