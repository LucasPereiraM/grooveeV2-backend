package com.groovee.api.controller;

import com.groovee.api.domain.artist.ArtistResponseDTO;
import com.groovee.api.domain.userList.UserList;
import com.groovee.api.domain.userList.UserListRequestDTO;
import com.groovee.api.domain.userList.UserListResponseDTO;
import com.groovee.api.services.UserListService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/list")
public class UserListController {

    private final UserListService userListService;

    public UserListController(UserListService userListService){
        this.userListService = userListService;
    }

    @PostMapping
    public ResponseEntity<UserListResponseDTO> create(@RequestBody @Valid UserListRequestDTO request, @AuthenticationPrincipal Jwt jwt){
        UUID userId = UUID.fromString(jwt.getSubject());
        UserList userList = userListService.createUserList(request, userId);
        return ResponseEntity.ok(new UserListResponseDTO(userList));
    }

    @GetMapping
    public ResponseEntity<List<UserListResponseDTO>> getLists(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        List<UserListResponseDTO> allLists = this.userListService.getLists(page, size);
        return ResponseEntity.ok(allLists);
    }
}
