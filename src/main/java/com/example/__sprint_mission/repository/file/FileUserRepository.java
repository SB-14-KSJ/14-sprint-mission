package com.example.__sprint_mission.repository.file;

import com.example.__sprint_mission.entity.Channel;
import com.example.__sprint_mission.entity.Message;
import com.example.__sprint_mission.entity.User;
import com.example.__sprint_mission.repository.ChannelRepository;
import com.example.__sprint_mission.repository.MessageRepository;
import com.example.__sprint_mission.repository.UserRepository;

import java.io.*;
import java.util.*;

public class FileUserRepository implements UserRepository {
    private final String filePath = "users.ser";
    private Map<UUID, User> data;

    public FileUserRepository() {
        this.data = loadData();
    }

    @SuppressWarnings("unchecked")
    private Map<UUID, User> loadData() {
        File file = new File(filePath);
        if (!file.exists()) {
            return new HashMap<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (Map<UUID, User>) ois.readObject();
        } catch (Exception e) {
            return new HashMap<>();
        }
    }

    private void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User save(User user) {
        data.put(user.getId(), user);
        saveData();
        return user;
    }

    @Override
    public Optional<User> findById(UUID id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public void deleteById(UUID id) {
        data.remove(id);
        saveData();
    }
}


