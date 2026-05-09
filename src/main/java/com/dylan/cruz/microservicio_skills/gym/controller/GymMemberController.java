package com.dylan.cruz.microservicio_skills.gym.controller;

import com.dylan.cruz.microservicio_skills.gym.dto.GymMemberPatchDTO;
import com.dylan.cruz.microservicio_skills.gym.dto.GymMemberRequestDTO;
import com.dylan.cruz.microservicio_skills.gym.dto.GymMemberResponseDTO;
import com.dylan.cruz.microservicio_skills.gym.service.GymMemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/gym-members")
@RequiredArgsConstructor
public class GymMemberController {

    private final GymMemberService service;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllMembers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            HttpServletRequest request) {
        Page<GymMemberResponseDTO> members = service.getAllMembers(PageRequest.of(page, size));
        
        String baseUrl = request.getRequestURL().toString();
        String nextUrl = members.hasNext() ? baseUrl + "?page=" + (members.getNumber() + 1) + "&size=" + members.getSize() : null;
        String prevUrl = members.hasPrevious() ? baseUrl + "?page=" + (members.getNumber() - 1) + "&size=" + members.getSize() : null;
        
        Map<String, Object> pagination = new HashMap<>();
        pagination.put("current_page", members.getNumber() + 1);
        pagination.put("last_page", members.getTotalPages() == 0 ? 1 : members.getTotalPages());
        pagination.put("per_page", members.getSize());
        pagination.put("total", members.getTotalElements());
        pagination.put("next", nextUrl);
        pagination.put("prev", prevUrl);
        
        Map<String, Object> response = new HashMap<>();
        response.put("data", members.getContent());
        response.put("pagination", pagination);
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GymMemberResponseDTO> getMemberById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getMemberById(id));
    }

    @PostMapping
    public ResponseEntity<GymMemberResponseDTO> createMember(@Valid @RequestBody GymMemberRequestDTO requestDTO) {
        return new ResponseEntity<>(service.createMember(requestDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GymMemberResponseDTO> updateMember(
            @PathVariable UUID id,
            @Valid @RequestBody GymMemberRequestDTO requestDTO) {
        return ResponseEntity.ok(service.updateMember(id, requestDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GymMemberResponseDTO> patchMember(
            @PathVariable UUID id,
            @Valid @RequestBody GymMemberPatchDTO patchDTO) {
        return ResponseEntity.ok(service.patchMember(id, patchDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteMember(@PathVariable UUID id) {
        service.deleteMember(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Miembro eliminado con éxito");
        return ResponseEntity.ok(response);
    }
}
