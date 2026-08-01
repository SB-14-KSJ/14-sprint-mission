package com.example.__sprint_mission.service.jcf;

import com.example.__sprint_mission.entity.User;
import com.example.__sprint_mission.service.UserService;

import java.util.*;

public class JCFUserService implements UserService {

    private final Map<UUID, User> data;

    public JCFUserService() {
        this.data = new HashMap<>();
    }

    @Override
    public User create(String username, String email) {
        User user = new User(username, email);
        data.put(user.getId(), user);
        return user;
    }

    @Override
    public User read(Object id) {
        return data.get(id);
    }

    @Override
    public List<User> readAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public User update(Object id, String username, String email) {
        User user = read(id);
        if (user != null) {
            user.update(username, email);
        }
        return user;
    }

    @Override
    public void delete(Object id) {
        data.remove(id);
    }
}