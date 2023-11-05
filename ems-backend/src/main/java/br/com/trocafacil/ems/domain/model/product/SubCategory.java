package br.com.trocafacil.ems.domain.model.product;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Entity(name = "SubCategory")
@Table(name = "sub_category")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dsSubCategory;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @Nonnull
    private Category category;

    @OneToMany(mappedBy = "subCategory")
    private ArrayList<Product> products;

}
