package ec.com.vaccination.backend.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class InventoryRequestDTO {
    private Integer id;
    private String brand;
    private Integer quantity;
    private boolean active;
}
