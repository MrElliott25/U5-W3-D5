package stefanonitti.demo.services;

import org.springframework.data.domain.PageRequest;
import stefanonitti.demo.entities.Prenotazione;
import stefanonitti.demo.entities.Utente;
import stefanonitti.demo.enums.Ruolo;
import stefanonitti.demo.exceptions.EmailInUseException;
import stefanonitti.demo.exceptions.NotFoundException;
import stefanonitti.demo.payloads.UtenteDTO;
import stefanonitti.demo.repositories.PrenotazioneRepository;
import stefanonitti.demo.repositories.UtenteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UtenteService {
    private final UtenteRepository utenteRepository;
    private final PasswordEncoder passwordEncoder;

    public UtenteService(UtenteRepository utenteRepository, PasswordEncoder passwordEncoder) {
        this.utenteRepository = utenteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Page<Utente> findAllUsers(Pageable pageable) {
        return utenteRepository.findAll(pageable);
    }


    public Utente saveUser(UtenteDTO body){
        if(this.utenteRepository.existsByEmail(body.email())) throw new EmailInUseException("Email già in uso!");
        Utente newUser = new Utente();
        newUser.setEmail(body.email());
        newUser.setCognome(body.cognome());
        newUser.setNome(body.nome());
        newUser.setRuolo(Ruolo.UTENTE);
        newUser.setPassword(passwordEncoder.encode(body.password()));
        newUser.setDataDiNascita(body.dataDiNascita());
        return this.utenteRepository.save(newUser);
    }

    public Utente findById(UUID id) {
        return utenteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Utente con id " + id + " non trovato"));
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
