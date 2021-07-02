package edu.school21.chat.models;

import java.util.Date;
import java.util.Objects;

public class Message {
    private Long id;
    private User author;
    private Chatroom chatroom;
    private String msgText;
    private Date msgDate;

    public Message (Long id, User author, Chatroom chatroom, String msgText, Date date) {
        this.id = id;
        this.author = author;
        this.chatroom = chatroom;
        this.msgText = msgText;
        this.msgDate = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id == message.id && author == message.author && chatroom == message.chatroom && Objects.equals(msgText, message.msgText) && Objects.equals(msgDate, message.msgDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, chatroom, msgText, msgDate);
    }

    @Override
    public String toString() {
        return "Message {" +
                "id=" + id +
                ", author=" + author +
                ", chatroom=" + chatroom +
                ", msgText='" + msgText + '\'' +
                ", msgDate=" + msgDate +
                '}';
    }
}
