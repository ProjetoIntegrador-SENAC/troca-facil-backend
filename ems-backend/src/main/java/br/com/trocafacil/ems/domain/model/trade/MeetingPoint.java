package br.com.trocafacil.ems.domain.model.trade;


import br.com.trocafacil.ems.domain.helpers.enums.DeliveryStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity(name = "MeetingPoint")
@Table(name = "meeting_point")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class MeetingPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @NotNull
    private Trade trade;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;
}
