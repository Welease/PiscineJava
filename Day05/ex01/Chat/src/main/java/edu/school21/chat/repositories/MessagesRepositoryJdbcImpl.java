package edu.school21.chat.repositories;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    private final HikariDataSource dataSource;

    public MessagesRepositoryJdbcImpl(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    private List<Chatroom> getCreatedRooms(long ownerID, Connection connection) throws SQLException {
        List<Chatroom> chatroomList = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet roomQuery = statement.executeQuery("SELECT * FROM users WHERE id = " + ownerID + ";");
        if (roomQuery.next()) {
            chatroomList.add(new Chatroom(roomQuery.getLong("ownerID"), roomQuery.getString("name_"), null, null));
        }
        return chatroomList;
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

            message_obj = new Message(id, user, chatroom, text, date);
            connection.close();
            return Optional.of(message_obj);
        }
        return Optional.empty();
    }
}
