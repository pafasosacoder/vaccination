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
@Table(name = "role")
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_id_seq")
    @SequenceGenerator(name = "role_id_seq", sequenceName = "role_id_seq")
    private Integer id;

    @NotNull
    private String name;

}
