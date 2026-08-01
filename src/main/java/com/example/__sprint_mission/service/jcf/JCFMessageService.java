package com.example.__sprint_mission.service.jcf;

import com.example.__sprint_mission.entity.Message;
import com.example.__sprint_mission.service.ChannelService;
import com.example.__sprint_mission.service.MessageService;
import com.example.__sprint_mission.service.UserService;
import java.util.*;

public class JCFMessageService implements MessageService {

    private final Map<UUID, Message> data;
    private final UserService userService;
    private final ChannelService channelService;

    // 의존성 주입(DI)을 통해 다른 도메인 서비스 참조
    public JCFMessageService(UserService userService, ChannelService channelService) {
        this.data = new HashMap<>();
        this.userService = userService;
        this.channelService = channelService;
    }

    @Override
    public Message create(String content, UUID authorId, UUID channelId) {
        // [심화] 작성자와 채널의 존재 여부 검증
        if (userService.read(authorId) == null) {
            throw new IllegalArgumentException("존재하지 않는 유저 ID입니다: " + authorId);
        }
        if (channelService.read(channelId) == null) {
            throw new IllegalArgumentException("존재하지 않는 채널 ID입니다: " + channelId);
        }

        Message message = new Message(content, authorId, channelId);
        data.put(message.getId(), message);
        return message;
    }

    @Override
    public Message read(Object id) {
        return data.get(id);
    }

    @Override
    public List<Message> readAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public Message update(Object id, String content) {
        Message message = read(id);
        if (message != null) {
            message.update(content);
        }
        return message;
    }

    @Override
    public void delete(Object id) {
        data.remove(id);
    }
}