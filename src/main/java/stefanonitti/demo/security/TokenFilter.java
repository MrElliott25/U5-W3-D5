package stefanonitti.demo.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import stefanonitti.demo.entities.Utente;
import stefanonitti.demo.exceptions.UnauthorizedException;
import stefanonitti.demo.services.UtenteService;

import java.io.IOException;
import java.util.UUID;

@Component
public class TokenFilter extends OncePerRequestFilter {
    private final TokenTools tokenTools;
    private final UtenteService utenteService;

    public TokenFilter(TokenTools tokenTools, UtenteService utenteService) {
        this.tokenTools = tokenTools;
        this.utenteService = utenteService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");
        if (authorization == null || !authorization.startsWith("Bearer")){
            throw new UnauthorizedException("Token nell'header non valido!");
        }
        String accessToken = authorization.replace("Bearer ", "");
        this.tokenTools.verifyToken(accessToken);

        UUID utenteId = this.tokenTools.extractIdFromToken(accessToken);
        Utente utente = this.utenteService.findById(utenteId);

        Authentication authentication = new UsernamePasswordAuthenticationToken(utente, null, utente.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }
}
