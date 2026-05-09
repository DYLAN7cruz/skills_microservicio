package com.dylan.cruz.microservicio_skills.gym.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
public class GymMemberPatchDTO {

    private String fullName;

    @Pattern(regexp = "^(Mensual|Trimestral|Anual)$", message = "El planType debe ser exactamente 'Mensual', 'Trimestral' o 'Anual'")
    private String planType;

    private Boolean hasPaid;

    private LocalDate registrationDate;
}
