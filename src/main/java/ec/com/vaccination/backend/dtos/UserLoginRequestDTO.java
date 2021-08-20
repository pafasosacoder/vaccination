package ec.com.vaccination.backend.dtos;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@Builder
public class UserLoginRequestDTO {

    @NotNull(message = "Identificador de usuario es requerido")
    @NotBlank(message = "Identificador de usuario es requerido")
    private String userName;

    @NotNull(message = "Contraseña es requerida")
    @NotBlank(message = "Contraseña es requerida")
    private String password;

}
