package stefanonitti.demo.controllers;

import org.springframework.validation.annotation.Validated;
import stefanonitti.demo.entities.Utente;
import stefanonitti.demo.payloads.LoginDTO;
import stefanonitti.demo.payloads.UtenteDTO;
import stefanonitti.demo.services.AuthService;
import stefanonitti.demo.services.UtenteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authentication")
public class AuthController {

    private final UtenteService utenteService;
    private final AuthService authService;

    public AuthController(UtenteService utenteService, AuthService authService){
        this.authService = authService;
        this.utenteService = utenteService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Utente register(@RequestBody @Validated UtenteDTO body) {
        return utenteService.saveUser(body);
    }

    @PostMapping("/login")
    public String login(@RequestBody @Validated LoginDTO body) {
        return authService.verifyAndGenerateToken(body);
    }
}
