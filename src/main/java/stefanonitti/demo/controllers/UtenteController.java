package stefanonitti.demo.controllers;

import stefanonitti.demo.entities.Evento;
import stefanonitti.demo.entities.Utente;
import stefanonitti.demo.payloads.UtenteDTO;
import stefanonitti.demo.services.EventoService;
import stefanonitti.demo.services.UtenteService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UtenteController {

    private final UtenteService utenteService;
    private final EventoService eventoService;

    public UtenteController(UtenteService utenteService, EventoService eventoService){
        this.eventoService = eventoService;
        this.utenteService = utenteService;
    }

    @GetMapping
    public Page<Utente> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "cognome") String sortBy
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return utenteService.findAllUsers(pageable);
    }

    @GetMapping("/{id}")
    public Utente getById(@PathVariable UUID id) {
        return utenteService.findById(id);
    }

    @PutMapping("/{id}")
    public Utente update(@PathVariable UUID id, @RequestBody @Valid UtenteDTO body) {
        Utente utenteModificato = new Utente();
        utenteModificato.setNome(body.nome());
        utenteModificato.setCognome(body.cognome());
        utenteModificato.setEmail(body.email());
        utenteModificato.setPassword(body.password());
        utenteModificato.setDataDiNascita(body.dataDiNascita());

        return utenteService.update(id, utenteModificato);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        utenteService.deleteById(id);
    }

    @GetMapping("/{idOrganizzatore}/eventi")
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    public Page<Evento> getEventi(
            @PathVariable UUID idOrganizzatore,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "data") String sortBy
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return eventoService.findAllEventsByOrganizzatore(idOrganizzatore, pageable);
    }
}
