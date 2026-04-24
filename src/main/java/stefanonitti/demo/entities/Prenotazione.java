package stefanonitti.demo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "prenotazioni")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    @Column(name = "data_prenotazione")
    private LocalDate dataPrenotazione;
    @ManyToOne
    @JoinColumn(name = "id_evento")
    private Evento evento;
    @ManyToOne
    @JoinColumn(name = "id_utente")
    private Utente utente;
}
