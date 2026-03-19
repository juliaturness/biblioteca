package ifsc.julia.backend.controllers;

import ifsc.julia.backend.dtos.UserRequestDTO;
import ifsc.julia.backend.dtos.UserResponseDTO;
import ifsc.julia.backend.models.User;
import ifsc.julia.backend.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/me")
    public ResponseEntity<?> currentUser (@AuthenticationPrincipal Jwt jwt){
        String auth0id = jwt.getSubject();

        User user = userService.findByAuth0Id(auth0id);

        return ResponseEntity.ok(new UserResponseDTO(user));
    }

    @DeleteMapping("/me")
    public ResponseEntity<?> deleteUser (@AuthenticationPrincipal Jwt jwt){
        String auth0id = jwt.getSubject();
        userService.deleteUser(auth0id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/me")
    public ResponseEntity<UserResponseDTO> updateUser (@AuthenticationPrincipal Jwt jwt, @RequestBody UserRequestDTO dto){

        String auth0id = jwt.getSubject();

        try {
            User updateUser = userService.updateUser(auth0id, dto);
            return ResponseEntity.ok(new UserResponseDTO(updateUser));
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}