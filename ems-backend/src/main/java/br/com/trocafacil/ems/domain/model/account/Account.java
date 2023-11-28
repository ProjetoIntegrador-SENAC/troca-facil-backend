package br.com.trocafacil.ems.domain.model.account;

//import br.com.trocafacil.ems.domain.model.trade.Notification;
import br.com.trocafacil.ems.domain.model.product.Product;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Account")
@Table(name = "account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nonnull
    @Column(length = 100)
    private String fullName;

    @Column(length = 100)
    private String username;

    @Column
    private String phoneNumber;

    @Nonnull
    @OneToOne
    @JsonIgnore
    private User user;

    @Nonnull
    @OneToOne
    private Address address;

    @Nonnull
    @Column(length = 16, unique = true)
    private String document;

    @JsonIgnore
    @OneToMany(mappedBy = "account")
    private List<Product> products = new ArrayList<>();

}