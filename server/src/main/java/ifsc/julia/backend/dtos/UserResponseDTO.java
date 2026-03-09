package ifsc.julia.backend.dtos;

import com.sun.istack.NotNull;
import ifsc.julia.backend.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    @NotNull
    Long id;

    @NotNull
    String username;

    @NotNull
    String password;

    @NotNull
    String email;

    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
    }
}
