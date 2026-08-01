package com.example.__sprint_mission.repository.jcf;

import com.example.__sprint_mission.entity.Channel;
import com.example.__sprint_mission.entity.Message;
import com.example.__sprint_mission.entity.User;
import com.example.__sprint_mission.repository.ChannelRepository;
import com.example.__sprint_mission.repository.MessageRepository;
import com.example.__sprint_mission.repository.UserRepository;

import java.util.*;

public class JCFUserRepository implements UserRepository {
    private final Map<UUID, User> data = new HashMap<>();

    @Override
    public User save(User user) {
        data.put(user.getId(), user);
        return user;
    }

    @Override
    public Optional<User> findById(UUID id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public void deleteById(UUID id) {
        data.remove(id);
    }
}



