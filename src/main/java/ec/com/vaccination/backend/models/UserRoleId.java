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
public class UserRoleId implements Serializable {
    @NotNull
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name="role_id")
    private Role role;
}
