package school21.spring.service.repositories;

import school21.spring.service.models.User;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static java.lang.String.format;

public class UsersRepositoryJdbcImpl implements UsersRepository {
    public DataSource dataSource;
    private final String tableName;

    public UsersRepositoryJdbcImpl(DataSource dataSource, String tableName) {
        this.dataSource = dataSource;
        this.tableName = tableName;
    }

    @Override
    public Optional<User> findById(Long id) throws SQLException {
        Optional<User> user = Optional.empty();
        ResultSet resultSet = dataSource.getConnection()
                .createStatement()
                .executeQuery("SELECT  * FROM " + tableName + ";");
        while (resultSet.next()) {
            Long identifier = resultSet.getLong("id");
            if (id.equals(identifier)) {
                String email = resultSet.getString("email");
                user = Optional.of(new User(identifier, email));
                resultSet.close();
                return user;
            }
        }
        resultSet.close();
        return user;
    }

    @Override
    public Optional<User> findByEmail(String email) throws SQLException {
        Optional<User> user = Optional.empty();
        ResultSet resultSet = dataSource.getConnection()
                .createStatement()
                .executeQuery("SELECT  * FROM " + tableName + ";");
        while (resultSet.next()) {
            String findEmail = resultSet.getString("email");
            if (findEmail.equals(email)) {
                Long identifier = resultSet.getLong("id");
                user = Optional.of(new User(identifier, findEmail));
                resultSet.close();
                return user;
            }
        }
        resultSet.close();
        return user;
    }

    @Override
    public List<User> findAll() throws SQLException {
        List<User> listProducts = new ArrayList<>();
        ResultSet resultSet = dataSource.getConnection().
                createStatement().executeQuery("SELECT * FROM " + tableName + ";");
        while (resultSet.next()) {
            Long identifier = resultSet.getLong("id");
            String email = resultSet.getString("email");
            listProducts.add(new User(identifier, email));
        }
        resultSet.close();
        return listProducts;
    }

    @Override
    public void save(User entity) throws SQLException {
        String query = format("INSERT INTO %s (id, email) VALUES ('%d', %s);",
                tableName,
                entity.getId(),
                entity.getEmail());
        dataSource.getConnection().createStatement().executeUpdate(query);
    }

    @Override
    public void update(User entity) throws SQLException {
        String query = format("UPDATE %s SET email = '%s' WHERE id = %d;",
                tableName,
                entity.getEmail(),
                entity.getId());
        dataSource.getConnection().createStatement().executeUpdate(query);
    }

    @Override
    public void delete(Long id) throws SQLException {
        dataSource.getConnection()
                .createStatement()
                .executeUpdate("DELETE FROM " + tableName + " WHERE id = " + id + ";");
    }
}
