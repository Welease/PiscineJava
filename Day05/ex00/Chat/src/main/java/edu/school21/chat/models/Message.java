package edu.school21.chat.models;

import java.util.Date;
import java.util.Objects;

public class Message {
    private Long id;
    private Long authorID;
    private Long chatroomID;
    private String msgText;
    private Date msgDate;

    Message (String msgText, Date date) {
        this.msgText = msgText;
        this.msgDate = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id == message.id && authorID == message.authorID && chatroomID == message.chatroomID && Objects.equals(msgText, message.msgText) && Objects.equals(msgDate, message.msgDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authorID, chatroomID, msgText, msgDate);
    }

    @Override
    public String
    toString() {
        return "Message : " +
                "id=" + id +
                ", authorID=" + authorID +
                ", chatroomID=" + chatroomID +
                ", msgText='" + msgText + '\'' +
                ", msgDate=" + msgDate;
    }
}
