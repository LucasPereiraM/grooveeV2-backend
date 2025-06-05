package com.groovee.api.controller;

import com.groovee.api.domain.list.ListItem;
import com.groovee.api.domain.list.ListItemRequestDTO;
import com.groovee.api.domain.list.ListItemResponseDTO;
import com.groovee.api.services.ListItemService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/listItem")
public class ListItemController {

    private final ListItemService listItemService;

    public ListItemController(ListItemService listItemService){
        this.listItemService = listItemService;
    }

    @PostMapping
    public ResponseEntity<ListItemResponseDTO> create(@RequestBody @Valid ListItemRequestDTO request){
        ListItem listItem = listItemService.createListItem(request);
        return ResponseEntity.ok(new ListItemResponseDTO(listItem));
    }
}
