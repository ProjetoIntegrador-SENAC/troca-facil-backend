package br.com.trocafacil.ems.domain.model.account;

import br.com.trocafacil.ems.domain.model.product.Product;
//import br.com.trocafacil.ems.domain.model.trade.Notification;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Account")
@Table(name = "account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nonnull
    @Column(length = 100)
    private String name;

    @Nonnull
    @Column(length = 100)
    private String surname;

    @Nonnull
    @OneToOne
    private User user;

    @Nonnull
    @OneToOne
    private Address address;

    @Nonnull
    @Column(length = 16, unique = true)
    private String document;

    private String photoPath;

//    @OneToMany(mappedBy = "account")
//    private ArrayList<Product> products;

}