package com.groovee.api.services;

import com.groovee.api.domain.userList.UserList;
import com.groovee.api.domain.userList.UserListRequestDTO;
import com.groovee.api.domain.userList.UserListResponseDTO;
import com.groovee.api.domain.user.User;
import com.groovee.api.repositories.ListRepository;
import com.groovee.api.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserListService {

    private final ListRepository listRepository;
    private final UserRepository userRepository;

    public UserListService(ListRepository listRepository, UserRepository userRepository){
        this.listRepository = listRepository;
        this.userRepository = userRepository;
    }

    public UserList createUserList(UserListRequestDTO data, UUID userId){
        UserList userList = new UserList();
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        userList.setUser(user);
        userList.setName(data.name());
        userList.setDescription(data.description());

        return listRepository.save(userList);
    }

    public List<UserListResponseDTO> getLists(int page, int size){
        Pageable pageable = PageRequest.of(page,size);
        Page<UserList> listsPage = this.listRepository.findAll(pageable);
        return listsPage.map(UserListResponseDTO::new).getContent();
    }
}
