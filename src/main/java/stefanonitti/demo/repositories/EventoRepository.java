package stefanonitti.demo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import stefanonitti.demo.entities.Evento;
import stefanonitti.demo.entities.Utente;

import java.util.Optional;
import java.util.UUID;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    Page<Evento> findByOrganizzatoreId(UUID idOrganizzatore, Pageable pageable);
}
