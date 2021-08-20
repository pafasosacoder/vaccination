package ec.com.vaccination.backend.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class UserResponseDTO {

    private String name;
    private String surName;
    private LocalDate birthDate;
    private String password;
    private String identification;
    private String email;
    private Integer sicknessType;
    private List<String> errorList;
    private String authorizationToken;
}
