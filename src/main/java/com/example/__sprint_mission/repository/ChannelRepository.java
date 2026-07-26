package com.example.__sprint_mission.repository;

import com.example.__sprint_mission.entity.Channel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ChannelRepository {
    Channel save(Channel channel);
    Optional<Channel> findById(UUID id);
    List<Channel> findAll();
    void deleteById(UUID id);
}
