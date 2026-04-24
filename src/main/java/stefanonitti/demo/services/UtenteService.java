package stefanonitti.demo.services;

import stefanonitti.demo.entities.Utente;
import stefanonitti.demo.repositories.UtenteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UtenteService {
    private UtenteRepository utenteRepository;
    private PasswordEncoder passwordEncoder;

    public UtenteService(UtenteRepository utenteRepository, PasswordEncoder passwordEncoder) {
        this.utenteRepository = utenteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Page<Utente> findAllUsers(Pageable pageable) {
        return utenteRepository.findAll(pageable);
    }

    public Utente findById(UUID id) {
        return utenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utente con id " + id + " non trovato"));
    }

    public Utente update(UUID id, Utente utente) {
        Utente u = this.findById(id);
        u.setNome(utente.getNome());
        u.setCognome(utente.getCognome());
        u.setEmail(utente.getEmail());
        u.setRuolo(utente.getRuolo());
        return utenteRepository.save(u);
    }

    public void deleteById(UUID id) {
        Utente trovato = this.findById(id);
        utenteRepository.delete(trovato);
    }

    public Utente findByEmail(String email){
        return this.utenteRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Email non trovata"));
    }
}
