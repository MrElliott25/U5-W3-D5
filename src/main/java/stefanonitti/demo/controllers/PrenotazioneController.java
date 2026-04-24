package stefanonitti.demo.controllers;

import org.springframework.validation.annotation.Validated;
import stefanonitti.demo.entities.Prenotazione;
import stefanonitti.demo.payloads.PrenotazioneDTO;
import stefanonitti.demo.services.PrenotazioneService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
public class PrenotazioneController {

    private final PrenotazioneService prenotazioneService;


    public PrenotazioneController(PrenotazioneService prenotazioneService){
        this.prenotazioneService = prenotazioneService;
    }

    @GetMapping
    public Page<Prenotazione> getAllReservations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
        return prenotazioneService.findAllReservations(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Prenotazione create(@RequestBody @Validated PrenotazioneDTO body) {
        return prenotazioneService.createReservation(body);
    }

    @GetMapping("/{id}")
    public Prenotazione getById(@PathVariable Long id) {
        return prenotazioneService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        prenotazioneService.deleteById(id);
    }
}
