package com.example.__sprint_mission.service;

import com.example.__sprint_mission.entity.User;

import java.util.List;

public interface UserService {
    User create(String username, String email);
    User read(Object id);
    List<User> readAll();
    User update(Object id, String username, String email);
    void delete(Object id);
}