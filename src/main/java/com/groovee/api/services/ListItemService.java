package com.groovee.api.services;

import com.groovee.api.domain.userList.*;
import com.groovee.api.repositories.ListItemRepository;
import com.groovee.api.repositories.ListRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ListItemService {

    private final ListItemRepository listItemRepository;
    private final ListRepository listRepository;

    public ListItemService(ListItemRepository listItemRepository, ListRepository listRepository){
        this.listItemRepository = listItemRepository;
        this.listRepository = listRepository;
    }

    public ListItem createListItem(ListItemRequestDTO data){
        ListItem listItem = new ListItem();
        UserList userList = listRepository.findById(data.listId()).orElseThrow(() -> new RuntimeException("Lista não encontrada"));

        listItem.setUserList(userList);
        listItem.setEntityId(data.entityId());
        listItem.setEntityType(data.entityType());

        return listItemRepository.save(listItem);
    }

    public List<ListItemResponseDTO> getListItems(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<ListItem> listItemsPage = this.listItemRepository.findAll(pageable);
        return listItemsPage.map(ListItemResponseDTO::new).getContent();
    }

    public ListItemResponseDTO updateListItem(UUID id, ListItemUpdateDTO dto){
        ListItem listItem = listItemRepository.findById(id).orElseThrow(() -> new RuntimeException("List Item not found"));

        if(dto.getEntityType() != null) listItem.setEntityType(dto.getEntityType());
        if(dto.getEntityId() != null) listItem.setEntityId(dto.getEntityId());

        ListItem updatedListItem = listItemRepository.save(listItem);
        return new ListItemResponseDTO(updatedListItem);
    }

    public void deleteListItem(UUID id){
        ListItem listItem = listItemRepository.findById(id).orElseThrow(() -> new RuntimeException("List item not found"));
        listItemRepository.delete(listItem);
    }
}
