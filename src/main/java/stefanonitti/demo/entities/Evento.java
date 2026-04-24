package stefanonitti.demo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "eventi")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    private String titolo;
    private String descrizione;
    private LocalDate data;
    private String luogo;
    private int ingressi;
    @ManyToOne
    @JoinColumn(name = "id_organizzatore")
    private Utente organizzatore;
    @OneToMany(mappedBy = "evento")
    private List<Prenotazione> prenotazioni;
}
