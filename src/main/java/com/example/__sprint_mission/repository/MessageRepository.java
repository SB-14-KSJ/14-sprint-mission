package com.example.__sprint_mission.repository;

import com.example.__sprint_mission.entity.Message;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MessageRepository {
    Message save(Message message);
    Optional<Message> findById(UUID id);
    List<Message> findAll();
    void deleteById(UUID id);
}
