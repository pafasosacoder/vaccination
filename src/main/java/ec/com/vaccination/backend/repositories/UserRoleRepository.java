package ec.com.vaccination.backend.repositories;

import ec.com.vaccination.backend.models.UserRole;
import ec.com.vaccination.backend.models.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {

    Optional<List<UserRole>> findByIdUserId(Long userId);
}
