package ec.com.vaccination.backend.repositories;

import ec.com.vaccination.backend.models.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    Optional<List<Inventory>> findByActiveTrue();
}
