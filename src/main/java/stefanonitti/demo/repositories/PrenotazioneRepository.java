package stefanonitti.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import stefanonitti.demo.entities.Prenotazione;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
}
