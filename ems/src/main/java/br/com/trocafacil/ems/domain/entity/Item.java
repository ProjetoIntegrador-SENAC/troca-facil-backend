package br.com.trocafacil.ems.domain.entity;

import jakarta.persistence.*;
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
    private long account_id;
    private String product;
    private BigDecimal price;
    private int amount;
    private String curCondition;
    private String urlImages;
    private String status;
    private String category;
    private String subCategory;
    private String cep;
}
