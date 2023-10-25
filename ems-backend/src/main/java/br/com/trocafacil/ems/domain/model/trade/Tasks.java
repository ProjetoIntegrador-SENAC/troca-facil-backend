package br.com.trocafacil.ems.domain.model.trade;


import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Trade")
@Table(name = "trades")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String tipo;
}
