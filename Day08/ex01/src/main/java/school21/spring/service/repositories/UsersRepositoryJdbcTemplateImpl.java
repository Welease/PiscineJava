package school21.spring.service.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import static java.lang.String.format;

public class UsersRepositoryJdbcTemplateImpl extends JdbcTemplate implements UsersRepository {
    private final String      tableName;
    RowMapper<User> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> {
        return new User(resultSet.getLong("id"), resultSet.getString("email"));
    };

    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource, String tableName) {
        super(dataSource);
        this.tableName = tableName;
    }

    @Override
    public Optional<User> findById(Long id) throws SQLException {
        try {
            User user = super.queryForObject(format("SELECT * FROM %s WHERE id = %d", tableName, id), ROW_MAPPER);
            return Optional.of(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByEmail(String email) throws SQLException {
        try {
            User user = super.queryForObject(format("SELECT * FORM %s WHERE email = '%s';", tableName, email), ROW_MAPPER);
            return (Optional.ofNullable(user));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAll() throws SQLException {
        return super.query(format("SELECT * FROM %s;", tableName), ROW_MAPPER);
    }

    @Override
    public void save(User entity) throws SQLException {
        super.update(format("INSERT INTO %s VALUES(%s);", tableName, entity.getEmail()));
    }

    @Override
    public void update(User entity) throws SQLException {
        super.update(format("UPDATE %s SET email = '%s' WHERE id = %d;",  tableName, entity.getEmail(), entity.getId()));
    }

    @Override
    public void delete(Long id) throws SQLException {
        super.update(format("DELETE FROM %s WHERE id = %d;", tableName, id));
    }
}
