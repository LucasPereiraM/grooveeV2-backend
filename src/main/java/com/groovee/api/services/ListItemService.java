package com.groovee.api.services;

import com.groovee.api.domain.list.List;
import com.groovee.api.domain.list.ListItem;
import com.groovee.api.domain.list.ListItemRequestDTO;
import com.groovee.api.repositories.ListItemRepository;
import com.groovee.api.repositories.ListRepository;
import org.springframework.stereotype.Service;

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
        List list = listRepository.findById(data.listId()).orElseThrow(() -> new RuntimeException("Lista não encontrada"));

        listItem.setList(list);
        listItem.setEntityId(data.entityId());
        listItem.setEntityType(data.entityType());

        return listItemRepository.save(listItem);
    }
}
