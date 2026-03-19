package ifsc.julia.backend.repositories;

import ifsc.julia.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByAuth0Id(String auth0Id);

}
