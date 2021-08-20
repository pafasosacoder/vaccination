package ec.com.vaccination.backend.services;

import ec.com.vaccination.backend.dtos.InventoryRequestDTO;
import ec.com.vaccination.backend.dtos.InventoryResponseDTO;
import ec.com.vaccination.backend.models.Inventory;
import ec.com.vaccination.backend.operations.InventoryOperations;
import ec.com.vaccination.backend.repositories.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventoryService implements InventoryOperations {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public ResponseEntity<List<InventoryResponseDTO>> getList() {
        Optional<List<Inventory>> inventoryList = inventoryRepository.findByActiveTrue();

        if (!inventoryList.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }

        List<InventoryResponseDTO> inventoryDTOList = inventoryList.get().stream().map(item ->
            InventoryResponseDTO.builder()
                .id(item.getId())
                .brand(item.getBrand())
                .quantity(item.getQuantity())
                .build()).collect(Collectors.toList());

        return new ResponseEntity<>(inventoryDTOList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<InventoryResponseDTO> save(InventoryRequestDTO inventoryRequest) {

        Inventory value = null;
        if (inventoryRequest.getId() != null) {

            Optional<Inventory> inventoryItem = inventoryRepository.findById(inventoryRequest.getId());

            if (!inventoryItem.isPresent()) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            value = inventoryItem.get();

            value.setActive(inventoryRequest.isActive());
            value.setBrand(inventoryRequest.getBrand());
            value.setQuantity(inventoryRequest.getQuantity());

        } else {

            value = Inventory.builder()
                    .active(true)
                    .brand(inventoryRequest.getBrand())
                    .quantity(inventoryRequest.getQuantity())
                    .build();

        }

        inventoryRepository.saveAndFlush(value);

        return new ResponseEntity<>(InventoryResponseDTO.builder().build(), HttpStatus.OK);
    }
}
