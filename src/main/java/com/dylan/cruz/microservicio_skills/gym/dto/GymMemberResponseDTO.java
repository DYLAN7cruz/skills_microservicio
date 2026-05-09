package com.dylan.cruz.microservicio_skills.gym.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class GymMemberResponseDTO {
    private UUID id;
    private String fullName;
    private String planType;
    private Boolean hasPaid;
    private LocalDate registrationDate;
}
