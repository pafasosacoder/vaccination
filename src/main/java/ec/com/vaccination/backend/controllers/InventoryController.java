package ec.com.vaccination.backend.controllers;

import ec.com.vaccination.backend.dtos.InventoryRequestDTO;
import ec.com.vaccination.backend.dtos.InventoryResponseDTO;
import ec.com.vaccination.backend.operations.InventoryOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InventoryController {

    @Autowired
    private InventoryOperations inventoryOperations;

    @GetMapping("/inventory/list")
    public ResponseEntity<List<InventoryResponseDTO>> getList() {

        return inventoryOperations.getList();
    }

    @PostMapping("/inventory/save")
    public ResponseEntity<InventoryResponseDTO> save(@RequestBody InventoryRequestDTO inventoryRequest) {

        return inventoryOperations.save(inventoryRequest);
    }


}
