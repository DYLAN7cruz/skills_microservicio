package com.dylan.cruz.microservicio_skills.gym.repository;

import com.dylan.cruz.microservicio_skills.gym.entity.GymMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GymMemberRepository extends JpaRepository<GymMember, UUID> {
}
