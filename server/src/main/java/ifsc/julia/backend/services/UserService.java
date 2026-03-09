package ifsc.julia.backend.services;

import ifsc.julia.backend.models.User;
import ifsc.julia.backend.dtos.UserRequestDTO;
import ifsc.julia.backend.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(UserRequestDTO dto){
        if(userRepository.findByEmail(dto.getEmail()).isPresent()){
            throw new RuntimeException("User already exists");
        }

        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setUsername(dto.getUsername());

        return userRepository.save(user);
    }

    public User login(String email, String password){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if(user.getPassword() == null){
            throw new RuntimeException("Password is null");
        }
        if(!user.getPassword().equals(password)){
            throw new RuntimeException("Password does not match");
        }
        return user;
    }
}


