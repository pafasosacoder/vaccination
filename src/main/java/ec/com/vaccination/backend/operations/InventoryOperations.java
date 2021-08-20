package ec.com.vaccination.backend.operations;

import ec.com.vaccination.backend.dtos.InventoryRequestDTO;
import ec.com.vaccination.backend.dtos.InventoryResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InventoryOperations {
    ResponseEntity<List<InventoryResponseDTO>> getList ();

    ResponseEntity<InventoryResponseDTO> save (InventoryRequestDTO inventoryRequest);
}
