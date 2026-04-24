package stefanonitti.demo.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record EventoDTO(
        @NotBlank(message = "Diamogli un nome a sto evento dai")
        String titolo,

        @NotBlank(message = "Descrivimelo un po'...")
        String descrizione,

        @NotNull(message = "E quando lo facciamo sto evento?")
        LocalDate data,

        @NotBlank(message = "E dove lo facciamo sto evento?")
        String luogo,

        @NotNull(message = "A meno che tu non lo faccia all'aperto, ho bisogno di un numero di ingressi")
        int ingressi,

        @NotNull(message = "Ho bisogno di sapere chi devo bastonare se le cose vanno male, dammi l'id dell'organizzatore no?")
        UUID idOrganizzatore
) {}
