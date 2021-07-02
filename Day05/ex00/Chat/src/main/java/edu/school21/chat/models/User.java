package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class User {
    private Long id;
    private final String login;
    private final String password;
    private List<Chatroom> createdByUserChatrooms;
    private List<Chatroom> userChatrooms;

    User (String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(login, user.login) && Objects.equals(password, user.password)
                && Objects.equals(createdByUserChatrooms, user.createdByUserChatrooms) && Objects.equals(userChatrooms, user.userChatrooms
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, createdByUserChatrooms, userChatrooms);
    }

    @Override
    public String toString() {
        return "Users : " +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", created rooms=" + createdByUserChatrooms +
                ", user's chatrooms=" + userChatrooms;
    }
}
