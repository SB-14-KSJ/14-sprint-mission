package com.example.__sprint_mission.service.basic;

import com.example.__sprint_mission.entity.Message;
import com.example.__sprint_mission.repository.ChannelRepository;
import com.example.__sprint_mission.repository.MessageRepository;
import com.example.__sprint_mission.repository.UserRepository;
import com.example.__sprint_mission.service.MessageService;

import java.util.List;
import java.util.UUID;

public class BasicMessageService implements MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final ChannelRepository channelRepository;

    public BasicMessageService(MessageRepository messageRepository,
                               UserRepository userRepository,
                               ChannelRepository channelRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.channelRepository = channelRepository;
    }

    @Override
    public Message create(String content, UUID authorId, UUID channelId) {
        // 비즈니스 검증 로직
        if (userRepository.findById(authorId).isEmpty()) {
            throw new IllegalArgumentException("존재하지 않는 유저입니다. ID: " + authorId);
        }
        if (channelRepository.findById(channelId).isEmpty()) {
            throw new IllegalArgumentException("존재하지 않는 채널입니다. ID: " + channelId);
        }

        Message message = new Message(content, authorId, channelId);
        return messageRepository.save(message);
    }

    @Override
    public Message read(Object id) {
        return messageRepository.findById((UUID) id).orElse(null);
    }

    @Override
    public List<Message> readAll() {
        return messageRepository.findAll();
    }

    @Override
    public Message update(Object id, String content) {
        Message message = read(id);
        if (message != null) {
            message.update(content);
            messageRepository.save(message);
        }
        return message;
    }

    @Override
    public void delete(Object id) {
        messageRepository.deleteById((UUID) id);
    }
}
