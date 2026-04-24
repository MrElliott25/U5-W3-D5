package stefanonitti.demo.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
        @NotBlank(message = "E con che mail facciamo il login?")
        @Email(message = "Mi dai una mail come si deve?")
        String email,

        @NotBlank(message = "E con che password entriamo?")
        String password
) {}
