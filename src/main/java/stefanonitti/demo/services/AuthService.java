package stefanonitti.demo.services;

import stefanonitti.demo.entities.Utente;
import stefanonitti.demo.payloads.LoginDTO;
import stefanonitti.demo.security.TokenTools;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final TokenTools tokenTools;
    private final UtenteService utenteService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(TokenTools tokenTools, UtenteService utenteService, PasswordEncoder passwordEncoder) {
        this.tokenTools = tokenTools;
        this.utenteService = utenteService;
        this.passwordEncoder = passwordEncoder;
    }

    public String verifyAndGenerateToken(LoginDTO body){

        try {
            Utente utente = this.utenteService.findByEmail(body.email());

            if (passwordEncoder.matches(body.password(), utente.getPassword())) {
                String accessToken = this.tokenTools.generateToken(utente);
                return accessToken;
            } else {
                throw new RuntimeException("Credenziali errate!");
            }

        }catch(Exception ex){
            throw new RuntimeException("Credenziali errate!");
        }
    }
}
