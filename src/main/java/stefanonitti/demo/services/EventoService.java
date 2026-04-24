package stefanonitti.demo.services;

import stefanonitti.demo.entities.Evento;
import stefanonitti.demo.entities.Utente;
import stefanonitti.demo.payloads.EventoDTO;
import stefanonitti.demo.repositories.EventoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import stefanonitti.demo.repositories.UtenteRepository;

import java.util.UUID;

@Service
public class EventoService {
    private final EventoRepository eventoRepository;
    private final UtenteService utenteService;

    public EventoService(EventoRepository eventoRepository, UtenteService utenteService){
        this.eventoRepository = eventoRepository;
        this.utenteService = utenteService;
    }

    public Page<Evento> findAllEvents(Pageable pageable) {
        return eventoRepository.findAll(pageable);
    }

    public Evento findById(Long id) {
        return eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento con id " + id + " non trovato"));
    }

    public Evento createEvent(EventoDTO body) {
        Utente organizzatore = utenteService.findById(body.idOrganizzatore());
        Evento nuovoEvento = new Evento();
        nuovoEvento.setTitolo(body.titolo());
        nuovoEvento.setDescrizione(body.descrizione());
        nuovoEvento.setData(body.data());
        nuovoEvento.setLuogo(body.luogo());
        nuovoEvento.setIngressi(body.ingressi());
        nuovoEvento.setOrganizzatore(organizzatore);
        return eventoRepository.save(nuovoEvento);
    }

    public Evento update(Long id, EventoDTO body) {
        Evento trovato = this.findById(id);
        trovato.setTitolo(body.titolo());
        trovato.setDescrizione(body.descrizione());
        trovato.setData(body.data());
        trovato.setLuogo(body.luogo());
        trovato.setIngressi(body.ingressi());
        return eventoRepository.save(trovato);
    }

    public void deleteById(Long id) {
        Evento trovato = this.findById(id);
        eventoRepository.delete(trovato);
    }

    public Page<Evento> findAllEventsByOrganizzatore(UUID idOrganizzatore, Pageable pageable) {
        return eventoRepository.findByOrganizzatoreId(idOrganizzatore, pageable);
    }
}
