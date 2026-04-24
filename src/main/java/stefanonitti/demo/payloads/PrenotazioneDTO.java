package stefanonitti.demo.payloads;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record PrenotazioneDTO(
        @NotNull(message = "Mi dici chi sta prenotando?")
        UUID idUtente,

        @NotNull(message = "Mi dici che cosa sta prenotando?")
        Long idEvento,

        @NotNull(message = "Allora data prenotazione potrei non metterlo, ma mi serve per fare dei test")
        LocalDate dataPrenotazione
) {}
