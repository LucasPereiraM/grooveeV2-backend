package com.groovee.api.controller;

import com.groovee.api.domain.list.List;
import com.groovee.api.domain.list.ListRequestDTO;
import com.groovee.api.domain.list.ListResponseDTO;
import com.groovee.api.services.ListService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/list")
public class ListController {

    private final ListService listService;

    public ListController(ListService listService){
        this.listService = listService;
    }

    @PostMapping
    public ResponseEntity<ListResponseDTO> create(@RequestBody @Valid ListRequestDTO request, @AuthenticationPrincipal Jwt jwt){
        UUID userId = UUID.fromString(jwt.getSubject());
        List list = listService.createList(request, userId);
        return ResponseEntity.ok(new ListResponseDTO(list));
    }
}
