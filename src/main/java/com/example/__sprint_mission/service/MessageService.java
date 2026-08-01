package com.example.__sprint_mission.service;

import com.example.__sprint_mission.entity.Message;

import java.util.List;
import java.util.UUID;

public interface MessageService {
    Message create(String content, UUID authorId, UUID channelId);
    Message read(Object id);
    List<Message> readAll();
    Message update(Object id, String content);
    void delete(Object id);
}