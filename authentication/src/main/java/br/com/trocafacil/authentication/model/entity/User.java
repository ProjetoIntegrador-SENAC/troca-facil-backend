package br.com.trocafacil.authentication.model.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "User")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Nonnull
    private String login;

    @Nonnull
    private String password;

}
