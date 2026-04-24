package stefanonitti.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import stefanonitti.demo.entities.Utente;

import java.util.Optional;
import java.util.UUID;

public interface UtenteRepository extends JpaRepository<Utente, UUID> {
    Optional<Utente> findByEmail(String email);
    boolean existsByEmail(String email);
}
