package com.example.__sprint_mission.service.basic;

import com.example.__sprint_mission.entity.Channel;
import com.example.__sprint_mission.entity.Message;
import com.example.__sprint_mission.entity.User;
import com.example.__sprint_mission.repository.ChannelRepository;
import com.example.__sprint_mission.repository.MessageRepository;
import com.example.__sprint_mission.repository.UserRepository;
import com.example.__sprint_mission.service.ChannelService;
import com.example.__sprint_mission.service.MessageService;
import com.example.__sprint_mission.service.UserService;

import java.util.List;
import java.util.UUID;

public class BasicUserService implements UserService {

    private final UserRepository userRepository;

    public BasicUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(String username, String email) {
        User user = new User(username, email);
        return userRepository.save(user);
    }

    @Override
    public User read(Object id) {
        return userRepository.findById((UUID) id).orElse(null);
    }

    @Override
    public List<User> readAll() {
        return userRepository.findAll();
    }

    @Override
    public User update(Object id, String username, String email) {
        User user = read(id);
        if (user != null) {
            user.update(username, email);
            userRepository.save(user);
        }
        return user;
    }

    @Override
    public void delete(Object id) {
        userRepository.deleteById((UUID) id);
    }
}


