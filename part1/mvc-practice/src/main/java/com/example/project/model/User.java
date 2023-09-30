package com.example.project.model;

import java.util.Objects;

public class User {
    private String userid;
    private String name;

    public User(String userid, String name) {
        this.userid = userid;
        this.name = name;
    }
    public boolean equalsUser(User user) {
        return this.equals(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userid, user.userid) && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userid, name);
    }
}
