package com.groovee.api.services;

import com.groovee.api.domain.list.List;
import com.groovee.api.domain.list.ListRequestDTO;
import com.groovee.api.domain.user.User;
import com.groovee.api.repositories.ListRepository;
import com.groovee.api.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ListService {

    private final ListRepository listRepository;
    private final UserRepository userRepository;

    public ListService(ListRepository listRepository, UserRepository userRepository){
        this.listRepository = listRepository;
        this.userRepository = userRepository;
    }

    public List createList(ListRequestDTO data, UUID userId){
        List list = new List();
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        list.setUser(user);
        list.setName(data.name());
        list.setDescription(data.description());

        return listRepository.save(list);
    }
}
