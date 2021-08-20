package ec.com.vaccination.backend.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class HttpMappingRoleId implements Serializable {
    @NotNull
    @ManyToOne
    @JoinColumn(name="http_mapping_id")
    private HttpMapping httpMapping;

    @NotNull
    @ManyToOne
    @JoinColumn(name="role_id")
    private Role role;
}
