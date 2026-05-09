package com.dylan.cruz.microservicio_skills.gym.service;

import com.dylan.cruz.microservicio_skills.gym.dto.GymMemberPatchDTO;
import com.dylan.cruz.microservicio_skills.gym.dto.GymMemberRequestDTO;
import com.dylan.cruz.microservicio_skills.gym.dto.GymMemberResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface GymMemberService {
    Page<GymMemberResponseDTO> getAllMembers(Pageable pageable);
    GymMemberResponseDTO getMemberById(UUID id);
    GymMemberResponseDTO createMember(GymMemberRequestDTO requestDTO);
    GymMemberResponseDTO updateMember(UUID id, GymMemberRequestDTO requestDTO);
    GymMemberResponseDTO patchMember(UUID id, GymMemberPatchDTO patchDTO);
    void deleteMember(UUID id);
}
