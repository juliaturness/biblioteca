package ifsc.julia.backend.controllers;

import ifsc.julia.backend.dtos.UserRequestDTO;
import ifsc.julia.backend.dtos.UserResponseDTO;
import ifsc.julia.backend.models.User;
import ifsc.julia.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserRequestDTO dto){

        User user = userService.save(dto);

        return  ResponseEntity.ok().body(new UserResponseDTO(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequestDTO dto){
        try {
            User user = userService.login(dto.getUsername(), dto.getPassword());
            return  ResponseEntity.ok().body(new UserResponseDTO(user));

        } catch (RuntimeException e) {
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
