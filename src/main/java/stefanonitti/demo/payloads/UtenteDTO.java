package stefanonitti.demo.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record UtenteDTO(
        @NotBlank(message = "Ce l'hai un nome?")
        String nome,

        @NotBlank(message = "Se hai un nome penso tu abbia un cognome..")
        String cognome,

        @NotBlank(message = "Bro la mail è importantissima")
        @Email(message = "Dammi una mail valida su su")
        String email,

        @NotBlank(message = "Non vuoi proteggere i tuoi dati?")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message =
        "La password deve contenere almeno un carattere minuscolo, uno maiuscolo, un numero e un carattere speciale, inoltre deve avere almeno 8 caratteri!")
        String password,

        @NotNull(message = "Sarai pur nato qualche giorno no?")
        LocalDate dataDiNascita
) {}
