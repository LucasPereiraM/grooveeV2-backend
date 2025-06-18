package com.groovee.api.controller;

import com.groovee.api.domain.userList.ListItem;
import com.groovee.api.domain.userList.ListItemRequestDTO;
import com.groovee.api.domain.userList.ListItemResponseDTO;
import com.groovee.api.domain.userList.ListItemUpdateDTO;
import com.groovee.api.services.ListItemService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping
    public ResponseEntity<List<ListItemResponseDTO>> getListItems(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        List<ListItemResponseDTO> allListItems = this.listItemService.getListItems(page, size);
        return ResponseEntity.ok(allListItems);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ListItemResponseDTO> updateListItem(@PathVariable UUID id, @RequestBody ListItemUpdateDTO dto){
        ListItemResponseDTO updateListItem = listItemService.updateListItem(id, dto);
        return ResponseEntity.ok(updateListItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ListItemResponseDTO> deleteListItem(@PathVariable UUID id){
        listItemService.deleteListItem(id);
        return ResponseEntity.noContent().build();
    }
}
