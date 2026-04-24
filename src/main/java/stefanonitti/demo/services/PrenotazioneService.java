package stefanonitti.demo.services;

import org.springframework.data.domain.PageRequest;
import stefanonitti.demo.entities.Evento;
import stefanonitti.demo.entities.Prenotazione;
import stefanonitti.demo.entities.Utente;
import stefanonitti.demo.exceptions.NotFoundException;
import stefanonitti.demo.payloads.PrenotazioneDTO;
import stefanonitti.demo.repositories.PrenotazioneRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PrenotazioneService {
    private final PrenotazioneRepository prenotazioneRepository;
    private final UtenteService utenteService;
    private final EventoService eventoService;

    public PrenotazioneService(PrenotazioneRepository prenotazioneRepository, UtenteService utenteService, EventoService eventoService){
        this.prenotazioneRepository = prenotazioneRepository;
        this.utenteService = utenteService;
        this.eventoService = eventoService;
    }

    public Page<Prenotazione> findAllReservations(Pageable pageable) {
        return prenotazioneRepository.findAll(pageable);
    }

    public Prenotazione findById(Long id) {
        return prenotazioneRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Prenotazione non trovata"));
    }

    public Prenotazione createReservation(PrenotazioneDTO body) {
        Prenotazione nuovaPrenotazione = new Prenotazione();
        nuovaPrenotazione.setDataPrenotazione(body.dataPrenotazione());
        Utente u = utenteService.findById(body.idUtente());
        Evento e = eventoService.findById(body.idEvento());
        nuovaPrenotazione.setEvento(e);
        nuovaPrenotazione.setUtente(u);
        return prenotazioneRepository.save(nuovaPrenotazione);
    }

    //Non inserisco l'update perchè non ha molto senso MODIFICARE una prenotazione per un evento, conviene piuttosto cancellarla, non ci sono molti parametri da modificare

    public void deleteById(Long id) {
        Prenotazione trovata = this.findById(id);
        prenotazioneRepository.delete(trovata);
    }

    public Page<Prenotazione> getPrenotazioniByUtente(UUID utenteId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return prenotazioneRepository.findByUtenteId(utenteId, pageable);
    }
}