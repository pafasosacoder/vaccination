package ec.com.vaccination.backend.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
public class UserSignUpRequestDTO {

    @NotNull(message = "Identificador de usuario es requerido")
    @NotBlank(message = "Identificador de usuario es requerido")
    private String userName;

    @NotNull(message = "Nombres son requeridos")
    @NotBlank(message = "Nombres son requeridos")
    @Pattern(regexp = "^[\\p{L} '-]+$", message = "Nombre solo debe contener letras")
    private String name;

    @NotNull(message = "Apellidos son requeridos")
    @NotBlank(message = "Apellidos son requeridos")
    @Pattern(regexp = "^[\\p{L} '-]+$", message = "Apellido solo debe contener letras")
    private String surName;

    @NotNull(message = "Fecha de nacimiento es requerida")
    private LocalDate birthDate;

    @NotNull(message = "Contraseña es requerida")
    @NotBlank(message = "Contraseña es requerida")
    private String password;

    @NotNull(message = "Numero de identificacion es requerido")
    @NotBlank(message = "Numero de identificacion es requerido")
    private String identification;

    @NotNull(message = "Correo electronico es requerido")
    @NotBlank(message = "Correo electronico es requerido")
    @Pattern(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",
    message = "Se requiere una direccion de Email valida")
    private String email;

    @NotNull(message = "Tipo de enfermedad es requerido")
    @NotBlank(message = "Tipo de enfermedad es requerido")
    private String sicknessType;
}
