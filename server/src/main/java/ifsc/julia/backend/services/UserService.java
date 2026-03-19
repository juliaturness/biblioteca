package ifsc.julia.backend.services;

import ifsc.julia.backend.dtos.UserRequestDTO;
import ifsc.julia.backend.models.User;
import ifsc.julia.backend.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // calls the controller whenever an authenticated request arrives
    public User syncUser(String auth0Id, String email, String name) {
        // checks whether the user already exists in our database using the Auth0 ID.
        return userRepository.findByAuth0Id(auth0Id)
                .orElseGet(() -> {
                    // se não existe, cria o login
                    User newUser = new User();
                    newUser.setAuth0Id(auth0Id);
                    newUser.setEmail(email);
                    newUser.setUsername(name);
                    return userRepository.save(newUser);
                });
    }

    public User findByAuth0Id(String auth0Id) {
        return userRepository.findByAuth0Id(auth0Id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Transactional
    public void deleteUser(String auth0Id){
        User user = userRepository.findByAuth0Id(auth0Id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userRepository.delete(user);
    }

    @Transactional
    public User updateUser(String auth0Id, UserRequestDTO dto) {
        User user = userRepository.findByAuth0Id(auth0Id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUsername(dto.getUsername());

        return userRepository.save(user);
    }
}