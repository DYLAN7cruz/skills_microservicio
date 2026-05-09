package com.dylan.cruz.microservicio_skills.gym.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
public class GymMemberRequestDTO {

    @NotBlank(message = "El nombre completo es obligatorio")
    private String fullName;

    @NotBlank(message = "El tipo de plan es obligatorio")
    @Pattern(regexp = "^(Mensual|Trimestral|Anual)$", message = "El planType debe ser exactamente 'Mensual', 'Trimestral' o 'Anual'")
    private String planType;

    @NotNull(message = "Debe indicar si ha pagado o no (true/false)")
    private Boolean hasPaid;

    @NotNull(message = "La fecha de registro es obligatoria")
    private LocalDate registrationDate;
}
