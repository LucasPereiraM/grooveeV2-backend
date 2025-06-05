package com.groovee.api.services;

import com.groovee.api.domain.seeker.Seeker;
import com.groovee.api.domain.seeker.SeekerRequestDTO;
import com.groovee.api.domain.user.User;
import com.groovee.api.repositories.SeekerRepository;
import com.groovee.api.repositories.UserRepository;
import org.springframework.stereotype.Service;

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
}
