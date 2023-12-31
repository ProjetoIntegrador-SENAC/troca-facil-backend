package br.com.trocafacil.model.entity;

import br.com.trocafacil.model.dto.AccountDto;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "Account")
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

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
    @Column(length = 16)
    // anotação para unico
    private String document;

    @OneToMany(mappedBy = "account")
    private List<Product> products;

    public Account(AccountDto accountDto){
        this.name = accountDto.name();
        this.surname = accountDto.surname();
        this.document = accountDto.document();
        this.user= accountDto.user();
    }
}
