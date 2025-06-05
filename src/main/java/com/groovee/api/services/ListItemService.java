package com.groovee.api.services;

import com.groovee.api.domain.artist.Artist;
import com.groovee.api.domain.artist.ArtistResponseDTO;
import com.groovee.api.domain.userList.ListItemResponseDTO;
import com.groovee.api.domain.userList.UserList;
import com.groovee.api.domain.userList.ListItem;
import com.groovee.api.domain.userList.ListItemRequestDTO;
import com.groovee.api.repositories.ListItemRepository;
import com.groovee.api.repositories.ListRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
