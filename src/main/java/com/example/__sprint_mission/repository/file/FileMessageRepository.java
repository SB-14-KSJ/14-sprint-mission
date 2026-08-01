package com.example.__sprint_mission.repository.file;

import com.example.__sprint_mission.entity.Message;
import com.example.__sprint_mission.repository.MessageRepository;

import java.io.*;
import java.util.*;

public class FileMessageRepository implements MessageRepository {
    private final String filePath = "messages.ser";
    private Map<UUID, Message> data;

    public FileMessageRepository() {
        this.data = loadData();
    }

    @SuppressWarnings("unchecked")
    private Map<UUID, Message> loadData() {
        File file = new File(filePath);
        if (!file.exists()) {
            return new HashMap<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (Map<UUID, Message>) ois.readObject();
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
    public Message save(Message message) {
        data.put(message.getId(), message);
        saveData();
        return message;
    }

    @Override
    public Optional<Message> findById(UUID id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public List<Message> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public void deleteById(UUID id) {
        data.remove(id);
        saveData();
    }
}
