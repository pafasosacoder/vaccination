package ec.com.vaccination.backend.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "inventory")
public class Inventory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventory_id_seq")
    @SequenceGenerator(name = "inventory_id_seq", sequenceName = "inventory_id_seq")
    private Integer id;

    @NotNull
    private String brand;

    @NotNull
    private Integer quantity;

    @NotNull
    private boolean active;
}
