package com.example.__sprint_mission.service.jcf;

import com.example.__sprint_mission.entity.Channel;
import com.example.__sprint_mission.service.ChannelService;
import java.util.*;

public class JCFChannelService implements ChannelService {

    private final Map<UUID, Channel> data;

    public JCFChannelService() {
        this.data = new HashMap<>();
    }

    @Override
    public Channel create(String name, String description) {
        Channel channel = new Channel(name, description);
        data.put(channel.getId(), channel);
        return channel;
    }

    @Override
    public Channel read(Object id) {
        return data.get(id);
    }

    @Override
    public List<Channel> readAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public Channel update(Object id, String name, String description) {
        Channel channel = read(id);
        if (channel != null) {
            channel.update(name, description);
        }
        return channel;
    }

    @Override
    public void delete(Object id) {
        data.remove(id);
    }
}