package stefanonitti.demo.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import stefanonitti.demo.entities.Evento;
import stefanonitti.demo.payloads.EventoDTO;
import stefanonitti.demo.services.EventoService;
import stefanonitti.demo.services.UtenteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService){
        this.eventoService = eventoService;
    }

    @GetMapping
    public Page<Evento> getAllEvents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "data") String sortBy
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return eventoService.findAllEvents(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('ORGANIZZATORE', 'ADMIN')")
    public Evento create(@RequestBody @Validated EventoDTO body) {
        return eventoService.createEvent(body);
    }

    @GetMapping("/{id}")
    public Evento getById(@PathVariable Long id) {
        return eventoService.findById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ORGANIZZATORE', 'ADMIN')")
    public Evento update(@PathVariable Long id, @RequestBody @Validated EventoDTO body) {
        return eventoService.update(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        eventoService.deleteById(id);
    }
}
