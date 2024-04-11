package com.verise.veganapp.service;

import com.verise.veganapp.entities.User;
import com.verise.veganapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public Long saveData(User user)
    {
       return userRepository.save(user).getId();
    }
}
