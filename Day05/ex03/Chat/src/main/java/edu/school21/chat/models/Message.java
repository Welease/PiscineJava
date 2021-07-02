package edu.school21.chat.models;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class Message {
    private Long id;
    private User author;
    private Chatroom chatroom;
    private String msgText;
    private LocalDateTime msgDate;

    public Message (Long id, User author, Chatroom chatroom, String msgText, LocalDateTime date) {
        this.id = id;
        this.author = author;
        this.chatroom = chatroom;
        this.msgText = msgText;
        this.msgDate = date;
    }

    public Long getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public Chatroom getRoom() {
        return chatroom;
    }

    public String getText() {
        return msgText;
    }

    public LocalDateTime getDateTime() {
        return msgDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setRoom(Chatroom room) {
        this.chatroom = room;
    }

    public void setText(String text) {
        this.msgText = text;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.msgDate = dateTime;
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
