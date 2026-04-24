package stefanonitti.demo.services;

import stefanonitti.demo.entities.Prenotazione;
import stefanonitti.demo.repositories.PrenotazioneRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PrenotazioneService {
    private PrenotazioneRepository prenotazioneRepository;

    public PrenotazioneService(PrenotazioneRepository prenotazioneRepository){
        this.prenotazioneRepository = prenotazioneRepository;
    }

    public Page<Prenotazione> findAll(Pageable pageable) {
        return prenotazioneRepository.findAll(pageable);
    }

    public Prenotazione findById(Long id) {
        return prenotazioneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prenotazione non trovata"));
    }

    public Prenotazione create(Prenotazione nuovaPrenotazione) {
        return prenotazioneRepository.save(nuovaPrenotazione);
    }

    public Prenotazione update(Long id, Prenotazione prenotazioneModificata) {
        Prenotazione trovata = this.findById(id);
        trovata.setDataPrenotazione(prenotazioneModificata.getDataPrenotazione());
        trovata.setEvento(prenotazioneModificata.getEvento());
        trovata.setUtente(prenotazioneModificata.getUtente());
        return prenotazioneRepository.save(trovata);
    }

    public void deleteById(Long id) {
        Prenotazione trovata = this.findById(id);
        prenotazioneRepository.delete(trovata);
    }
}