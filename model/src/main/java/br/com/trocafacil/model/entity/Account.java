package br.com.trocafacil.model.entity;

import br.com.trocafacil.model.dto.AccountDto;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Account")
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Account {

    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private String id;

    @Nonnull
    private String name;

    @Nonnull
    private String surname;

    @Nonnull
    @OneToOne
    @MapsId
    private User user;

    @Nonnull
    // anotação para unico
    private String document;

    public Account(AccountDto accountDto){
        this.name = accountDto.name();
        this.surname = accountDto.surname();
        this.document = accountDto.document();
        this.user = accountDto.user();
    }
}
