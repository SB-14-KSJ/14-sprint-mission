package com.example.__sprint_mission.entity;


public class User extends BaseEntity {

    private String username;
    private String email;

    public User(String username, String email) {
        super();
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void update(String username, String email) {
        this.username = username;
        this.email = email;
        this.updatedAt = System.currentTimeMillis();
    }
}