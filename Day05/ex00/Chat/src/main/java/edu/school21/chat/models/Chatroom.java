package edu.school21.chat.models;

import java.util.Objects;

public class Chatroom {
    private Long id;
    private String name;
    private Long ownerID;

    Chatroom (String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        if (this == object) return true;
        Chatroom chatroom = (Chatroom) object;
        return id == chatroom.id && ownerID == chatroom.ownerID && Objects.equals(name, chatroom.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, ownerID);
    }

    @Override
    public String toString() {
        return "Chatroom - " + name + " :" +
                " id = " +  id  +
                " ownerID = " + ownerID;
    }
}
