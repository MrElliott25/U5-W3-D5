package stefanonitti.demo.services;

import stefanonitti.demo.entities.Evento;
import stefanonitti.demo.repositories.EventoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EventoService {
    private final EventoRepository eventoRepository;

    public EventoService(EventoRepository eventoRepository){
        this.eventoRepository = eventoRepository;
    }

    public Page<Evento> findAllEvents(Pageable pageable) {
        return eventoRepository.findAll(pageable);
    }
    public Evento findById(Long id) {
        return eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento con id " + id + " non trovato"));
    }

    public Evento createEvent(Evento nuovoEvento) {
        return eventoRepository.save(nuovoEvento);
    }

    public Evento update(Long id, Evento eventoModificato) {
        Evento trovato = this.findById(id);
        trovato.setTitolo(eventoModificato.getTitolo());
        trovato.setDescrizione(eventoModificato.getDescrizione());
        trovato.setData(eventoModificato.getData());
        trovato.setLuogo(eventoModificato.getLuogo());
        trovato.setIngressi(eventoModificato.getIngressi());
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
