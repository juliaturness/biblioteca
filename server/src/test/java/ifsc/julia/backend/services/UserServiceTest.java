package ifsc.julia.backend.services;

import ifsc.julia.backend.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Mock
    private final UserRepository userRepository;

    UserServiceTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    @DisplayName("should sync user successfully when everything is OK ")
    void syncUserCase1() {

    }

    @Test
    @DisplayName("should throw Exception when sync fails  ")
    void syncUserCase2() {
    }

    @Test
    void findByAuth0Id() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void updateUser() {
    }
}