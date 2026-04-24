package stefanonitti.demo.exceptions;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorsListDTO(
        String message,
        List<String> errors,
        LocalDateTime timestamp
) {}
