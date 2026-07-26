package com.example.__sprint_mission.service;

import com.example.__sprint_mission.entity.Channel;

import java.util.List;
import java.util.UUID;

public interface ChannelService {
    Channel create(String name, String description);
    Channel read(Object id);
    List<Channel> readAll();
    Channel update(Object id, String name, String description);
    void delete(Object id);
}