package br.edu.ufpel.mapacultural.Controller;

import br.edu.ufpel.mapacultural.Service.TokenService;
import br.edu.ufpel.mapacultural.dto.LoginDTO;
import br.edu.ufpel.mapacultural.dto.TokenDTO;
import jakarta.validation.Valid;
import org.antlr.v4.runtime.Token;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }


    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login (@Valid @RequestBody LoginDTO dto)
    {
        Authentication auth = authenticationManager.authenticate(UsernamePasswordAuthenticationToken
                .unauthenticated(dto.email(), dto.senha()));

        UserDetails usuario = (UserDetails) auth.getPrincipal();

        String token = tokenService.gerarToken(usuario);

        return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
    }
}
