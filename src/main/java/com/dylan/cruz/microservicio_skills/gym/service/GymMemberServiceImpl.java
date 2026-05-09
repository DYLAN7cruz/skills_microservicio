package com.dylan.cruz.microservicio_skills.gym.service;

import com.dylan.cruz.microservicio_skills.gym.dto.GymMemberPatchDTO;
import com.dylan.cruz.microservicio_skills.gym.dto.GymMemberRequestDTO;
import com.dylan.cruz.microservicio_skills.gym.dto.GymMemberResponseDTO;
import com.dylan.cruz.microservicio_skills.gym.entity.GymMember;
import com.dylan.cruz.microservicio_skills.gym.exception.ResourceNotFoundException;
import com.dylan.cruz.microservicio_skills.gym.repository.GymMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GymMemberServiceImpl implements GymMemberService {

    private final GymMemberRepository repository;

    @Override
    @Transactional(readOnly = true)
    public Page<GymMemberResponseDTO> getAllMembers(Pageable pageable) {
        return repository.findAll(pageable).map(this::mapToResponseDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public GymMemberResponseDTO getMemberById(UUID id) {
        GymMember member = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Miembro no encontrado con el ID: " + id));
        return mapToResponseDTO(member);
    }

    @Override
    @Transactional
    public GymMemberResponseDTO createMember(GymMemberRequestDTO requestDTO) {
        GymMember member = new GymMember();
        member.setFullName(requestDTO.getFullName());
        member.setPlanType(requestDTO.getPlanType());
        member.setHasPaid(requestDTO.getHasPaid());
        member.setRegistrationDate(requestDTO.getRegistrationDate());

        GymMember savedMember = repository.save(member);
        return mapToResponseDTO(savedMember);
    }

    @Override
    @Transactional
    public GymMemberResponseDTO updateMember(UUID id, GymMemberRequestDTO requestDTO) {
        GymMember member = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Miembro no encontrado con el ID: " + id));

        member.setFullName(requestDTO.getFullName());
        member.setPlanType(requestDTO.getPlanType());
        member.setHasPaid(requestDTO.getHasPaid());
        member.setRegistrationDate(requestDTO.getRegistrationDate());

        GymMember updatedMember = repository.save(member);
        return mapToResponseDTO(updatedMember);
    }

    @Override
    @Transactional
    public GymMemberResponseDTO patchMember(UUID id, GymMemberPatchDTO patchDTO) {
        GymMember member = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Miembro no encontrado con el ID: " + id));

        if (patchDTO.getFullName() != null) {
            member.setFullName(patchDTO.getFullName());
        }
        if (patchDTO.getPlanType() != null) {
            member.setPlanType(patchDTO.getPlanType());
        }
        if (patchDTO.getHasPaid() != null) {
            member.setHasPaid(patchDTO.getHasPaid());
        }
        if (patchDTO.getRegistrationDate() != null) {
            member.setRegistrationDate(patchDTO.getRegistrationDate());
        }

        GymMember updatedMember = repository.save(member);
        return mapToResponseDTO(updatedMember);
    }

    @Override
    @Transactional
    public void deleteMember(UUID id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Miembro no encontrado con el ID: " + id);
        }
        repository.deleteById(id);
    }

    private GymMemberResponseDTO mapToResponseDTO(GymMember member) {
        GymMemberResponseDTO dto = new GymMemberResponseDTO();
        dto.setId(member.getId());
        dto.setFullName(member.getFullName());
        dto.setPlanType(member.getPlanType());
        dto.setHasPaid(member.getHasPaid());
        dto.setRegistrationDate(member.getRegistrationDate());
        return dto;
    }
}
