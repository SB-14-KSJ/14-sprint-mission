package com.example.__sprint_mission.entity;

import java.util.UUID;

public class Channel extends BaseEntity {

    private String name;
    private String description;

    public Channel(String name, String description) {
        super();
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void update(String name, String description) {
        this.name = name;
        this.description = description;
        this.updatedAt = System.currentTimeMillis();
    }
}
