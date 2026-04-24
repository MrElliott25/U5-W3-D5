package stefanonitti.demo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import stefanonitti.demo.entities.Prenotazione;

import java.util.UUID;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    Page<Prenotazione> findByUtenteId(UUID idUtente, Pageable pageable);
}
