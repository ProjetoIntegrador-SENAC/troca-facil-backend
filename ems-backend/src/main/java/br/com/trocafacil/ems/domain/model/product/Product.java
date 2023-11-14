package br.com.trocafacil.ems.domain.model.product;

import br.com.trocafacil.ems.domain.helpers.enums.ProductCondition;
import br.com.trocafacil.ems.domain.helpers.enums.ProductStatus;
import br.com.trocafacil.ems.domain.model.trade.Trade;
import br.com.trocafacil.ems.domain.model.account.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity(name = "product")
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(exclude = {"tradesPosted"})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @NotNull
    private String name;

    @NotNull
    private BigDecimal price;

    @Min(1)
    @Max(50)
    private Integer amount;

    @Temporal(TemporalType.DATE)
    private LocalDate date;

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

    @OneToMany(mappedBy = "productPosted")
    @JsonIgnore
    private List<Trade> tradesPosted;

}
