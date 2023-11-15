package br.com.trocafacil.ems.domain.model.account;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity(name = "Address")
@Table(name = "address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String zip;

    @NotNull
    private int number;

    private String district;

    private String dsAddress;

    private String complement;




}
