package school21.spring.service.repositories;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {
    public Optional<T> findById(Long id) throws SQLException;
    public List<T> findAll() throws SQLException;
    public void save(T entity) throws SQLException;
    public void update(T entity) throws SQLException;
    public void delete(Long id) throws SQLException;
}
