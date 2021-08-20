package ec.com.vaccination.backend.repositories;

import ec.com.vaccination.backend.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
