package com.example.__sprint_mission.service.basic;

import com.example.__sprint_mission.entity.Channel;
import com.example.__sprint_mission.repository.ChannelRepository;
import com.example.__sprint_mission.service.ChannelService;

import java.util.List;
import java.util.UUID;

public class BasicChannelService implements ChannelService {

    private final ChannelRepository channelRepository;

    public BasicChannelService(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }

    @Override
    public Channel create(String name, String description) {
        Channel channel = new Channel(name, description);
        return channelRepository.save(channel);
    }

    @Override
    public Channel read(Object id) {
        return channelRepository.findById((UUID) id).orElse(null);
    }

    @Override
    public List<Channel> readAll() {
        return channelRepository.findAll();
    }

    @Override
    public Channel update(Object id, String name, String description) {
        Channel channel = read(id);
        if (channel != null) {
            channel.update(name, description);
            channelRepository.save(channel);
        }
        return channel;
    }

    @Override
    public void delete(Object id) {
        channelRepository.deleteById((UUID) id);
    }
}
