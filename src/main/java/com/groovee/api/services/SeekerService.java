package com.groovee.api.services;

import com.groovee.api.domain.seeker.Seeker;
import com.groovee.api.domain.seeker.SeekerRequestDTO;
import com.groovee.api.domain.seeker.SeekerResponseDTO;
import com.groovee.api.domain.user.User;
import com.groovee.api.repositories.SeekerRepository;
import com.groovee.api.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeekerService {
    private final SeekerRepository seekerRepository;
    private final UserRepository userRepository;

    public SeekerService(SeekerRepository seekerRepository, UserRepository userRepository){
        this.seekerRepository = seekerRepository;
        this.userRepository = userRepository;
    }

    public Seeker CreateSeeker(SeekerRequestDTO data){
        Seeker seeker = new Seeker();
        User user = userRepository.findById(data.userId()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        seeker.setUser(user);
        seeker.setQueryText(data.queryText());

        return seekerRepository.save(seeker);
    }
    public List<SeekerResponseDTO> getSeekers(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Seeker> seekersPage = this.seekerRepository.findAll(pageable);
        return seekersPage.map(SeekerResponseDTO::new).getContent();
    }
}
