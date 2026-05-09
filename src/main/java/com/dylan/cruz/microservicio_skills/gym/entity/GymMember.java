package com.dylan.cruz.microservicio_skills.gym.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "gym_member")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GymMember {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String planType;

    @Column(nullable = false)
    private Boolean hasPaid;

    @Column(nullable = false)
    private LocalDate registrationDate;
}
