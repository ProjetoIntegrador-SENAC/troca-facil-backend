package br.com.trocafacil.ems.domain.model.trade;

import br.com.trocafacil.ems.domain.model.account.Account;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity(name = "Notification")
@Table(name = "notification")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String msg;

    private String uri;

    @NotNull
    @ManyToOne
    private Account account;

}
