package edu.school21.chat.repositories;

import edu.school21.chat.models.Message;

import java.sql.SQLException;
import java.util.Optional;

public interface MessagesRepository {
    void save(Message message) throws SQLException;
    Optional<Message> findById(Long id) throws SQLException;
}
