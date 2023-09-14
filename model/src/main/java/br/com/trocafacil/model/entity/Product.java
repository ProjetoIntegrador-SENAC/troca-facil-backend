package br.com.trocafacil.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(length = 50)
    private String name;

    private String description;

    @Column(name = "condition_product", length = 30)
    private String condition;

    @Column(length = 30)
    private String category;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

}
