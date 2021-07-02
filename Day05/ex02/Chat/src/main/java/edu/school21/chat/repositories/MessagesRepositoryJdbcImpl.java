package edu.school21.chat.repositories;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;


import java.time.LocalDateTime;
import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    private final HikariDataSource dataSource;

    public MessagesRepositoryJdbcImpl(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    private void  setMessageId(Message message) throws SQLException {
        Long id = message.getId();
        User author = message.getAuthor();
        Chatroom room = message.getRoom();
        String text = message.getText();
        LocalDateTime dateTime = message.getDateTime();
        String save_message;

        Connection connection = dataSource.getConnection();

        save_message = "SELECT id FROM public.messages ORDER BY DATETIME DESC LIMIT 1;";
        Statement statement2 = connection.createStatement();
        ResultSet query = statement2.executeQuery(save_message);
        if (query.next()) {
            message.setId(query.getLong("id"));
        }
        connection.close();
    }

    public void save(Message message) throws SQLException {
        Long id = message.getId();
        User author = message.getAuthor();
        Chatroom room = message.getRoom();
        String text = message.getText();
        LocalDateTime dateTime = message.getDateTime();
        String save_message, insertingAuthor;

        Connection connection = dataSource.getConnection();
        if (id != null)
            save_message = "INSERT INTO messages (id, authorID, chatroomID, text, dateTime) VALUES (" + id + ", " + author.getId() + ", " + room.getId() + ", \'" + text + "\', \'" + dateTime + "\');";
        else {
            save_message = "INSERT INTO messages (authorID, chatroomID, text, dateTime) VALUES (" + author.getId() + ", " + room.getId() + ", \'" + text + "\', \'" + dateTime + "\');";
//            insertingAuthor = "INSERT INTO users (id, login, password) VALUES (" + author.getId() + ", \'" + author.getLogin() + "\', \'" + author.getPassword() + "\');";
//            PreparedStatement statement = connection.prepareStatement(insertingAuthor);
//            statement.executeUpdate();
        }
        PreparedStatement pst ;
        pst = connection.prepareStatement(save_message);
        pst.executeUpdate();
        setMessageId(message);
        connection.close();
    }

    @Override
    public Optional<Message> findById(Long id) throws SQLException {
        Long            author = 0L;
        Long            room = 0L;
        String          text = null;
        Timestamp       date = null;
        User            user = null;
        Chatroom        chatroom = null;
        Message         message_obj = null;

        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet messageQuery = statement.executeQuery("SELECT * FROM messages WHERE id = " + id + ";");
        if (messageQuery.next()) {
            author = messageQuery.getLong("authorID");
            room = messageQuery.getLong("chatroomID");
            text =  messageQuery.getString("text");
            date = messageQuery.getTimestamp("dateTime");

            Statement statement1 = connection.createStatement();
            ResultSet authorQuery = statement1.executeQuery("SELECT * FROM users WHERE id = " + author + ";");
            if (authorQuery.next()) {
                user = new User(author, authorQuery.getString("login"), authorQuery.getString("password"), null, null);
            }

            Statement statement2 = connection.createStatement();
            ResultSet roomQuery = statement2.executeQuery("SELECT * FROM chatrooms WHERE id = " + room + ";");
            if (roomQuery.next()) {
                chatroom = new Chatroom(room, roomQuery.getString("name_"), null, null);
            }

            message_obj = new Message(id, user, chatroom, text, date.toLocalDateTime());
            connection.close();
            return Optional.of(message_obj);
        }
        return Optional.empty();
    }
}
