package ifsc.julia.backend.controllers;

import ifsc.julia.backend.dtos.UserResponseDTO;
import ifsc.julia.backend.models.User;
import ifsc.julia.backend.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // rota para fazer login no Auth0
    @PostMapping("/sync")
    public ResponseEntity<UserResponseDTO> syncUser(@AuthenticationPrincipal Jwt jwt) {

        // extrai os dados validados diretamente do Token do Auth0
        String auth0Id = jwt.getSubject();
        String email = jwt.getClaimAsString("email");
        String username = jwt.getClaimAsString("nickname");

        // chama o Service para salvar no banco ou apenas buscar o usuário
        User user = userService.syncUser(auth0Id, email, username);

        // retorna os dados do banco para o frontend
        return ResponseEntity.ok(new UserResponseDTO(user));
    }
}