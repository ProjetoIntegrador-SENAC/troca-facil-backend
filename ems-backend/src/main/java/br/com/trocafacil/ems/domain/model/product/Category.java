package br.com.trocafacil.ems.domain.model.product;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;

@Entity(name = "category")
@Table(name = "category")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dsCategory;

    @OneToMany(mappedBy = "category")
    private ArrayList<SubCategory> subCategorys;

    @OneToMany(mappedBy = "category")
    private ArrayList<Product> products;

}
